package com.carrentalapplication.service.impl;

import com.carrentalapplication.model.carbooking.BookedCarsDateReport;
import com.carrentalapplication.model.carbooking.CarBookingRequest;
import com.carrentalapplication.model.carrentaluser.CarRentalUser;
import com.carrentalapplication.repository.CarBookingRepository;
import com.carrentalapplication.service.CarBookingService;
import com.carrentalapplication.utils.CarBookedAlreadyException;
import com.carrentalapplication.utils.CarNotFoundException;
import com.carrentalapplication.utils.IllegalArgException;
import com.carrentalapplication.utils.UserNotFoundException;
import com.carrentalapplication.model.cars.Car;
import com.carrentalapplication.model.carbooking.CarBooking;
import com.carrentalapplication.model.carbooking.BookedCarsPerHourReportResult;
import com.carrentalapplication.model.carbooking.BookedCarsReport;
import com.carrentalapplication.repository.CarRentalUserRepository;
import com.carrentalapplication.repository.CarRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
public class CarBookingServiceImplTest {

    Car car;
    @Autowired
    private CarBookingService carBookingService;
    @MockBean
    private CarBookingRepository carBookingRepository;
    @MockBean
    private CarRentalUserRepository carRentalUserRepository;
    @MockBean
    private CarRepository carRepository;

    @Test
    void testCreateCarBooking() throws IllegalArgException {
        LocalDateTime localDateTime = LocalDateTime.now();
        CarBooking carBooking = createCarBookingBean(localDateTime, "ABO129");
        CarBookingRequest carBookingRequest = new CarBookingRequest(100.0, localDateTime, localDateTime.plusHours(1), "ABO123", "User1");

        Mockito.when(carBookingRepository.findAlreadyRegisterdBooking(carBookingRequest.getFromDate(), carBookingRequest.getToDate(), carBookingRequest.getCarNumberPlate())).
                thenReturn(carBooking);

        Assertions.assertThrows(CarBookedAlreadyException.class, () ->
                carBookingService.createBooking(carBookingRequest));

    }

    @Test
    void testCreateCarBookingUserNotFound() throws IllegalArgException {
        LocalDateTime localDateTime = LocalDateTime.now();
        CarBooking carBooking = createCarBookingBean(localDateTime, "ABO130");
        CarBookingRequest carBookingRequest = new CarBookingRequest(100.0, localDateTime, localDateTime.plusHours(1), "ABO123", "User1");

        Mockito.when(carBookingRepository.findAlreadyRegisterdBooking(carBookingRequest.getFromDate(), carBookingRequest.getToDate(), "ABO876")).thenReturn(carBooking);

        Assertions.assertThrows(UserNotFoundException.class, () ->
                carBookingService.createBooking(carBookingRequest));

    }

    @Test
    void testCreateCarBookingCarNotFound() throws IllegalArgException {
        LocalDateTime localDateTime = LocalDateTime.now();
        CarBooking carBooking = createCarBookingBean(localDateTime, "ABO130");
        CarBookingRequest carBookingRequest = new CarBookingRequest(100.0, localDateTime, localDateTime.plusHours(1), "ABO123", "User1");

        Mockito.when(carBookingRepository.findAlreadyRegisterdBooking(carBookingRequest.getFromDate(), carBookingRequest.getToDate(), "ABO876")).thenReturn(carBooking);
        Mockito.when(carRentalUserRepository.findById("User1")).thenReturn(Optional.of(new CarRentalUser("User1", "Jphn", "Doe", "pas12341223232115")));

        Assertions.assertThrows(CarNotFoundException.class, () ->
                carBookingService.createBooking(carBookingRequest));

    }

    @Test
    void testCreateCarBookingPassedTest() throws IllegalArgException, UserNotFoundException, CarBookedAlreadyException, CarNotFoundException {
        LocalDateTime localDateTime = LocalDateTime.now();
        CarBooking carBooking = createCarBookingBean(localDateTime, "ABO130");
        CarBookingRequest carBookingRequest = new CarBookingRequest(100.0, localDateTime, localDateTime.plusHours(1), "ABO123", "User1");

        Mockito.when(carBookingRepository.findAlreadyRegisterdBooking(carBookingRequest.getFromDate(), carBookingRequest.getToDate(), "ABO876")).thenReturn(carBooking);
        Mockito.when(carRentalUserRepository.findById("User1")).thenReturn(Optional.of(new CarRentalUser("User1", "Jphn", "Doe", "pas12341223232115")));
        Mockito.when(carRentalUserRepository.findById("User1")).thenReturn(Optional.of(new CarRentalUser("User1", "Jphn", "Doe", "pas12341223232185")));
        Mockito.when(carRepository.findById("ABO123")).thenReturn(Optional.of(new Car("ABO123", 45.0, localDateTime, localDateTime.plusDays(5))));

        carBookingService.createBooking(carBookingRequest);

    }

    @Test
    void testSearchBookedCars() throws IllegalArgException {
        LocalDateTime localDateTime = LocalDateTime.now();
        List<CarBooking> carBookings = new ArrayList<>();
        carBookings.add(createCarBookingBean(localDateTime, "ABO130"));
        carBookings.add(createCarBookingBean(localDateTime, "ABO131"));
        BookedCarsReport bookedCarsDateReport = new BookedCarsReport(localDateTime, localDateTime.plusHours(5), "User1", "ABO123");

        Mockito.when(carBookingRepository.searchCarsBooking(bookedCarsDateReport.getFromDate(), bookedCarsDateReport.getToDate(), "ABO123", "User1")).thenReturn(carBookings);

        List<Car> cars = carBookingService.searchBookedCars(bookedCarsDateReport);
        Assertions.assertEquals(2, cars.size());
        Assertions.assertEquals(new Car("ABO130", 200.0, localDateTime, localDateTime.plusHours(3)), cars.get(0));
        Assertions.assertEquals(new Car("ABO131", 200.0, localDateTime, localDateTime.plusHours(3)), cars.get(1));

    }

    @Test
    void testSearchBookedCarsBlank() throws IllegalArgException{
        LocalDateTime localDateTime = LocalDateTime.now();
        BookedCarsReport bookedCarsDateReport = new BookedCarsReport(localDateTime, localDateTime.plusHours(5), "User1", "ABO123");
        Mockito.when(carBookingRepository.searchCarsBooking(bookedCarsDateReport.getFromDate(), bookedCarsDateReport.getToDate(), "ABO123", "User1")).thenReturn(Collections.emptyList());

        List<Car> cars = carBookingService.searchBookedCars(bookedCarsDateReport);
        Assertions.assertEquals(0, cars.size());

    }

    @Test
    void testSearchTotalPayments() throws IllegalArgException{
        LocalDateTime localDateTime = LocalDateTime.now();
        List<CarBooking> carBookings = new ArrayList<>();
        carBookings.add(createCarBookingBean(localDateTime, "ABO130"));
        carBookings.add(createCarBookingBean(localDateTime, "ABO131"));
        BookedCarsDateReport bookedCarsDateReport = new BookedCarsDateReport(localDateTime, localDateTime.plusHours(5));

        Mockito.when(carBookingRepository.searchCarBookingForGivenDates(bookedCarsDateReport.getFromDate(), bookedCarsDateReport.getToDate())).thenReturn(carBookings);

        Double totalPayment = carBookingService.searchTotalPayment(bookedCarsDateReport);
        Assertions.assertEquals(600.0, totalPayment);

    }

    @Test
    void testSearchTotalPaymentsBlank() throws IllegalArgException{
        LocalDateTime localDateTime = LocalDateTime.now();
        BookedCarsDateReport bookedCarsDateReport = new BookedCarsDateReport(localDateTime, localDateTime.plusHours(5));

        Mockito.when(carBookingRepository.searchCarBookingForGivenDates(bookedCarsDateReport.getFromDate(), bookedCarsDateReport.getToDate())).thenReturn(Collections.emptyList());

        Double totalPayment = carBookingService.searchTotalPayment(bookedCarsDateReport);
        Assertions.assertEquals(0.0, totalPayment);

    }

    @Test
    void testReportPerHour() throws IllegalArgException{
        LocalDateTime localDateTime = LocalDateTime.now();
        List<CarBooking> carBookings = new ArrayList<>();
        CarBooking carBooking = createCarBookingBean(localDateTime, "ABO130");
        carBooking.setBookingDate(LocalDateTime.now().plusHours(1));
        carBookings.add(carBooking);
        createCarBookingBean(localDateTime, "ABO131");
        carBooking.setBookingDate(LocalDateTime.now().plusHours(2));
        carBookings.add(carBooking);
        BookedCarsDateReport bookedCarsDateReport = new BookedCarsDateReport(localDateTime, localDateTime.plusHours(5));

        Mockito.when(carBookingRepository.searchCarBookingForGivenDates(bookedCarsDateReport.getFromDate(), bookedCarsDateReport.getToDate())).thenReturn(carBookings);

        List<BookedCarsPerHourReportResult> bookedCarsPerHourReportResults = carBookingService.searchBookedCarPerHour(bookedCarsDateReport);
        Assertions.assertEquals(5, bookedCarsPerHourReportResults.size());
    }

    private CarBooking createCarBookingBean(LocalDateTime localDateTime, String numberPlate) throws IllegalArgException {
        return new CarBooking(300.0, localDateTime, localDateTime.plusHours(1), new CarRentalUser("User98", "Jphn", "Doe", "pas12341223232115"), new Car(numberPlate, 200.0, localDateTime, localDateTime.plusHours(3)));
    }

    @TestConfiguration
    static class CarBookingServiceConfiguration {
        @Bean
        public CarBookingService carBookingService() {
            return new CarBookingServiceImpl();
        }

    }

}

