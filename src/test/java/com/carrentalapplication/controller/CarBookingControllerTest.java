package com.carrentalapplication.controller;

import com.carrentalapplication.model.carbooking.BookedCarsDateReport;
import com.carrentalapplication.model.carbooking.CarBookingRequest;
import com.carrentalapplication.model.carrentaluser.CarRentalUser;
import com.carrentalapplication.service.CarBookingService;
import com.carrentalapplication.utils.CarBookedAlreadyException;
import com.carrentalapplication.utils.CarNotFoundException;
import com.carrentalapplication.utils.IllegalArgException;
import com.carrentalapplication.utils.UserNotFoundException;
import com.carrentalapplication.model.cars.Car;
import com.carrentalapplication.model.carbooking.CarBooking;
import com.carrentalapplication.model.carbooking.BookedCarsPerHourReportResult;
import com.carrentalapplication.model.carbooking.BookedCarsReport;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class CarBookingControllerTest {
    @InjectMocks
    CarBookingController carBookingController;

    @Mock
    CarBookingService carBookingService;

    @Test
    public void testCreateCarBooking() throws IllegalArgException, UserNotFoundException, CarBookedAlreadyException, CarNotFoundException {
        LocalDateTime localDateTime = LocalDateTime.now();
        CarBooking carBooking = createCarBookingBean(localDateTime, "ABC123");
        CarBookingRequest carBookingRequest = new CarBookingRequest(100.0, localDateTime, localDateTime.plusHours(1), "ABO123", "User1");

        Mockito.when(carBookingService.createBooking(carBookingRequest)).thenReturn(carBooking);

        CarBooking carBookingFound = carBookingController.createCarBooking(carBookingRequest);

        Assertions.assertEquals(carBooking, carBookingFound);

    }

    @Test
    public void testSearchCarBooking() throws IllegalArgException {
        LocalDateTime localDateTime = LocalDateTime.now();
        Car car = new Car("ABO123", 200.0, localDateTime, localDateTime.plusHours(2));
        BookedCarsReport bookedCarsReport=new BookedCarsReport(localDateTime,localDateTime.plusHours(2),"User1","ABC123");
        Mockito.when(carBookingService.searchBookedCars(bookedCarsReport)).thenReturn(Arrays.asList(car));

        List<Car> carsFound = carBookingController.searchBookedCars(bookedCarsReport);

        Assertions.assertEquals(car,  carsFound.get(0));
        Assertions.assertEquals(1,  carsFound.size());

    }

    @Test
    public void testSearchTotalPayment() throws IllegalArgException {
        LocalDateTime localDateTime = LocalDateTime.now();

        BookedCarsDateReport bookedCarsReport=new BookedCarsDateReport(localDateTime,localDateTime.plusHours(2));
        Mockito.when(carBookingService.searchTotalPayment(bookedCarsReport)).thenReturn(100.0);

        Double totalPayment = carBookingController.searchTotalPayment(bookedCarsReport);

        Assertions.assertEquals(100.0,  totalPayment);

    }


    @Test
    public void testSearchBookedCarPerHour() throws IllegalArgException {
        LocalDateTime localDateTime = LocalDateTime.now();

        BookedCarsDateReport bookedCarsReport=new BookedCarsDateReport(localDateTime,localDateTime.plusHours(2));
        BookedCarsPerHourReportResult bookedCarsPerHourReportResult=new BookedCarsPerHourReportResult(localDateTime,localDateTime.plusHours(1),4);
        Mockito.when(carBookingService.searchBookedCarPerHour(bookedCarsReport)).thenReturn(Arrays.asList(bookedCarsPerHourReportResult));

        List<BookedCarsPerHourReportResult> bookedCarsPerHourReportResultsFound = carBookingController.searchBookedCarPerHour(bookedCarsReport);

        Assertions.assertEquals(1,bookedCarsPerHourReportResultsFound.size());
        Assertions.assertEquals(bookedCarsPerHourReportResult,  bookedCarsPerHourReportResultsFound.get(0));

    }
    private CarBooking createCarBookingBean(LocalDateTime localDateTime, String numberPlate) throws IllegalArgException {
        return new CarBooking(300.0, localDateTime, localDateTime.plusHours(1), new CarRentalUser("User98", "Jphn", "Doe", "pas12341223232115"), new Car(numberPlate, 200.0, localDateTime, localDateTime.plusHours(3)));
    }
}
