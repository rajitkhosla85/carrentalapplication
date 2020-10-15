package com.carrentalapplication.controller;

import com.carrentalapplication.model.carbooking.BookedCarsDateReport;
import com.carrentalapplication.model.carbooking.CarBookingRequest;
import com.carrentalapplication.service.CarBookingService;
import com.carrentalapplication.utils.*;
import com.carrentalapplication.model.cars.Car;
import com.carrentalapplication.model.carbooking.CarBooking;
import com.carrentalapplication.model.Dates;
import com.carrentalapplication.model.carbooking.BookedCarsPerHourReportResult;
import com.carrentalapplication.model.carbooking.BookedCarsReport;
import com.carrentalapplication.repository.CarRentalUserRepository;
import com.carrentalapplication.repository.CarRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CarBookingController {
    private static final Logger LOGGER = LoggerFactory.getLogger(CarBookingController.class);
    @Autowired
    CarBookingService carBookingService;

    @Autowired
    CarRentalUserRepository carRentalUserRepository;

    @Autowired
    CarRepository carRepository;

    @PostMapping(value = "/bookings/createCarBooking", consumes = "application/json")
    public CarBooking createCarBooking(@RequestBody CarBookingRequest newCarBookingRequest) throws UserNotFoundException, CarBookedAlreadyException, CarNotFoundException {
        LOGGER.debug(CarApplicationsConstants.REQUEST_RECIEVED_IN_CREATE_CAR_BOOKING, newCarBookingRequest);
        return carBookingService.createBooking(newCarBookingRequest);

    }

    @GetMapping(value = "/bookings/searchBookedCars", consumes = "application/json")
    public List<Car> searchBookedCars(@RequestBody BookedCarsReport bookedCarsReport) {
        LOGGER.debug(CarApplicationsConstants.REQUEST_RECIEVED_IN_SEARCH_BOOKED_CARS, bookedCarsReport);
        return carBookingService.searchBookedCars(bookedCarsReport);
    }

    @GetMapping(value = "/bookings/searchTotalPayments", consumes = "application/json")
    public Double searchTotalPayment(@RequestBody BookedCarsDateReport bookedCarsReport) throws IllegalArgException {
        LOGGER.debug(CarApplicationsConstants.REQUEST_RECIEVED_IN_SEARCH_TOTAL_PAYMENT, bookedCarsReport);
        Dates.validateFromToDate(bookedCarsReport.getFromDate(), bookedCarsReport.getToDate());
        return carBookingService.searchTotalPayment(bookedCarsReport);
    }

    @GetMapping(value = "/bookings/searchBookedCarsPerHour", consumes = "application/json")
    public List<BookedCarsPerHourReportResult> searchBookedCarPerHour(@RequestBody BookedCarsDateReport bookedCarsReport) throws IllegalArgException {
        LOGGER.debug(CarApplicationsConstants.REQUEST_RECIEVED_IN_SEARCH_BOOKED_CAR_PER_HOUR, bookedCarsReport);
        Dates.validateFromToDate(bookedCarsReport.getFromDate(), bookedCarsReport.getToDate());
        return carBookingService.searchBookedCarPerHour(bookedCarsReport);
    }

}


