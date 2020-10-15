package com.carrentalapplication.model;


import com.carrentalapplication.model.carbooking.CarBookingRequest;
import com.carrentalapplication.model.carrentaluser.CarRentalUser;
import com.carrentalapplication.utils.CarApplicationsConstants;
import com.carrentalapplication.utils.IllegalArgException;
import com.carrentalapplication.model.carbooking.CarBooking;
import com.carrentalapplication.model.cars.Car;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class CarBookingTest {

    CarBooking carBooking;
    private Validator validator;

    @BeforeEach
    public void setUp() throws IllegalArgException {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
        LocalDateTime localDateTime = LocalDateTime.now();
        carBooking = createCarBookingBean(localDateTime, "ABC123");
    }

    @Test
    public void testBasicTest() {
        Set<ConstraintViolation<CarBooking>> violations = validator.validate(carBooking);
        Assertions.assertTrue(violations.isEmpty());
    }

    @Test
    public void testBookingCostLessThanZero() {

        carBooking.setCarBookingCost(0.0);
        Set<ConstraintViolation<CarBooking>> violations = validator.validate(carBooking);
        Assertions.assertEquals(1, violations.size());
        List<String> violationList = violations.stream().map(violation -> violation.getMessage()).collect(Collectors.toList());
        Assertions.assertEquals(CarApplicationsConstants.BOOKING_COST_GREATER_THAN_ZERO, violationList.get(0));
    }

    @Test
    public void testGetMethods() throws IllegalArgException {
        CarBooking newCarBooking = createCarBookingBean(carBooking.getFromDate(), "ABC123");
        Assertions.assertEquals(newCarBooking.getToDate(),carBooking.getToDate());
        Assertions.assertEquals(newCarBooking.getFromDate(),carBooking.getFromDate());
        Assertions.assertEquals(newCarBooking.getCarBookingCost(),carBooking.getCarBookingCost());
        Assertions.assertEquals(newCarBooking.getBookingDate(),carBooking.getBookingDate());
        Assertions.assertEquals(newCarBooking.getCar(),carBooking.getCar());
        Assertions.assertEquals(newCarBooking.getCarRentalUser(),carBooking.getCarRentalUser());

    }

    @Test
    public void testEquals() throws IllegalArgException {
        CarBooking newCarBooking = createCarBookingBean(carBooking.getFromDate(), "ABC123");
        Assertions.assertTrue(carBooking.equals(newCarBooking));
    }

    @Test
    public void testOtherConstructor() throws IllegalArgException {
        CarBookingRequest carBookingRequest = new CarBookingRequest(300.0, carBooking.getFromDate(), carBooking.getToDate(), "ABC123", "User1");
        CarBooking carBookingNew= new CarBooking(carBookingRequest, new CarRentalUser("User98", "Jphn", "Doe", "pas12341223232115"),new Car("ABC123", 200.0, carBooking.getCar().getFromDate(), carBooking.getCar().getToDate()));
        Assertions.assertTrue(carBooking.equals(carBookingNew));
    }

    private CarBooking createCarBookingBean(LocalDateTime localDateTime, String numberPlate) throws IllegalArgException {
        return new CarBooking(300.0, localDateTime, localDateTime.plusHours(1), new CarRentalUser("User98", "Jphn", "Doe", "pas12341223232115"), new Car(numberPlate, 200.0, localDateTime, localDateTime.plusHours(3)));
    }
}



