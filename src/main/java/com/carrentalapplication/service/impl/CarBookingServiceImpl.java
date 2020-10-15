package com.carrentalapplication.service.impl;

import com.carrentalapplication.model.carbooking.BookedCarsDateReport;
import com.carrentalapplication.model.carbooking.CarBookingRequest;
import com.carrentalapplication.model.carrentaluser.CarRentalUser;
import com.carrentalapplication.repository.CarBookingRepository;
import com.carrentalapplication.service.CarBookingService;
import com.carrentalapplication.utils.*;
import com.carrentalapplication.model.cars.Car;
import com.carrentalapplication.model.carbooking.CarBooking;
import com.carrentalapplication.model.carbooking.BookedCarsPerHourReportResult;
import com.carrentalapplication.model.carbooking.BookedCarsReport;
import com.carrentalapplication.repository.CarRentalUserRepository;
import com.carrentalapplication.repository.CarRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarBookingServiceImpl implements CarBookingService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CarBookingServiceImpl.class);
    @Autowired
    CarBookingRepository carBookingRepository;

    @Autowired
    CarRentalUserRepository carRentalUserRepository;

    @Autowired
    CarRepository carRepository;

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public CarBooking createBooking(CarBookingRequest newCarBookingRequest) throws CarBookedAlreadyException, UserNotFoundException, CarNotFoundException {

        verifyNoBookingThereForCar(newCarBookingRequest);
        CarRentalUser carRentalUser = fetchCarRentalUser(newCarBookingRequest.getCarBookingUserName());
        Car car = fetchCar(newCarBookingRequest.getCarNumberPlate());
        return carBookingRepository.save(generateCarBooking(newCarBookingRequest, carRentalUser, car));

    }

    private Car fetchCar(String carNumberPlate) throws CarNotFoundException {
        return carRepository.findById(carNumberPlate)
                .orElseThrow(() -> new CarNotFoundException(carNumberPlate));
    }

    private void verifyNoBookingThereForCar(CarBookingRequest newCarBookingRequest) throws CarBookedAlreadyException {
        CarBooking carBooking = carBookingRepository.findAlreadyRegisterdBooking(newCarBookingRequest.getFromDate(), newCarBookingRequest.getToDate(), newCarBookingRequest.getCarNumberPlate());
        if (carBooking != null) {
            LOGGER.info(CarApplicationsConstants.ALREADY_BOOKED_FOR_GIVEN_DATE_WITH_CAR_PLATE_NUMBER, newCarBookingRequest);
            throw new CarBookedAlreadyException(newCarBookingRequest.getCarNumberPlate());
        }
    }

    private CarRentalUser fetchCarRentalUser(String carRentalUserName) throws UserNotFoundException {
        return carRentalUserRepository.findById(carRentalUserName)
                .orElseThrow(() -> {
                    LOGGER.info(CarApplicationsConstants.NOT_FIND_USER + carRentalUserName);
                    return new UserNotFoundException(carRentalUserName);
                });
    }

    private CarBooking generateCarBooking(CarBookingRequest carBookingRequest, CarRentalUser carRentalUser, Car car) {
        return new CarBooking(carBookingRequest, carRentalUser, car);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Car> searchBookedCars(BookedCarsReport bookedCarsReport) {
        List<CarBooking> carBookings = carBookingRepository.searchCarsBooking(bookedCarsReport.getFromDate(), bookedCarsReport.getToDate(), bookedCarsReport.getCarNumberPlate(), bookedCarsReport.getCarRentalUser());
        return CarBooking.getCarsFromCarBookings(carBookings);
    }

    @Override
    @Transactional(readOnly = true)
    public Double searchTotalPayment(BookedCarsDateReport bookedCarsReport) {
        List<CarBooking> carBookings = carBookingRepository.searchCarBookingForGivenDates(bookedCarsReport.getFromDate(), bookedCarsReport.getToDate());
        return CarBooking.getTotalPayments(carBookings);
    }

    @Override
    @Transactional(readOnly = true)
    public List<BookedCarsPerHourReportResult> searchBookedCarPerHour(BookedCarsDateReport bookedCarsReport) throws IllegalArgException {
        List<CarBooking> carBookings = carBookingRepository.searchCarBookingForGivenDates(bookedCarsReport.getFromDate(), bookedCarsReport.getToDate());
        return getBookedCarsPerHourReportCount(carBookings, bookedCarsReport);

    }

    private List<BookedCarsPerHourReportResult> getBookedCarsPerHourReportCount(List<CarBooking> carBookings, BookedCarsDateReport bookedCarsReport) throws IllegalArgException {
        Collections.sort(carBookings);
        List<BookedCarsPerHourReportResult> bookedCarsPerHourReportResults = new ArrayList<>();
        LocalDateTime localDateTime = bookedCarsReport.getFromDate();
        while (localDateTime.isBefore(bookedCarsReport.getToDate())) {
            LocalDateTime plus1HourDateTime = localDateTime.plusHours(1);
            List<CarBooking> filteredCarBookings = carBookings.stream().filter(carBooking -> carBooking.getBookingDate().isAfter(plus1HourDateTime.minusHours(1)) && carBooking.getBookingDate().isBefore(plus1HourDateTime)).collect(Collectors.toList());
            bookedCarsPerHourReportResults.add(new BookedCarsPerHourReportResult(localDateTime, plus1HourDateTime, filteredCarBookings.size()));
            localDateTime = localDateTime.plusHours(1);
        }
        return bookedCarsPerHourReportResults;
    }

}


