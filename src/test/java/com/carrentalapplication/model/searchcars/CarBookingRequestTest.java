package com.carrentalapplication.model.searchcars;

import com.carrentalapplication.model.carbooking.CarBookingRequest;
import com.carrentalapplication.utils.IllegalArgException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

class CarBookingRequestTest {

    @Test
    void testBasics() throws IllegalArgException {

        LocalDateTime localDateTime = LocalDateTime.now();
        CarBookingRequest carBookingRequest = new CarBookingRequest(100.0, localDateTime, localDateTime.plusHours(1), "ABC123", "User1");
        Assertions.assertEquals(localDateTime, carBookingRequest.getFromDate());
        Assertions.assertEquals(localDateTime.plusHours(1), carBookingRequest.getToDate());
        Assertions.assertEquals("User1", carBookingRequest.getCarBookingUserName());
        Assertions.assertEquals("ABC123", carBookingRequest.getCarNumberPlate());
        Assertions.assertEquals(100.0, carBookingRequest.getCarBookingCost());

    }

    @Test
    void testCarBookingUserName() {
        LocalDateTime localDateTime = LocalDateTime.now();
        Assertions.assertThrows(IllegalArgException.class, () ->
                new CarBookingRequest(100.0, localDateTime, localDateTime.plusHours(1), "ABC123", ""));
        Assertions.assertThrows(IllegalArgException.class, () ->
                new CarBookingRequest(100.0, localDateTime, localDateTime.minusHours(1), "ABC123", "User1"));
        Assertions.assertThrows(IllegalArgException.class, () ->
                new CarBookingRequest(100.0, localDateTime, localDateTime.plusHours(1), "ABC123", "U"));
    }

    @Test
    void testCarBookingCost() {
        LocalDateTime localDateTime = LocalDateTime.now();
        Assertions.assertThrows(IllegalArgException.class, () ->
                new CarBookingRequest(0.0, localDateTime, localDateTime.plusHours(1), "ABC123", "User1"));
        Assertions.assertThrows(IllegalArgException.class, () ->
                new CarBookingRequest(-100.0, localDateTime, localDateTime.plusHours(1), "ABC123", "User1"));

    }

    @Test
    void testCarNumberPlate() {
        LocalDateTime localDateTime = LocalDateTime.now();
        Assertions.assertThrows(IllegalArgException.class, () ->
                new CarBookingRequest(100.0, localDateTime, localDateTime.plusHours(1), "", "User1"));
        Assertions.assertThrows(IllegalArgException.class, () ->
                new CarBookingRequest(100.0, localDateTime, localDateTime.plusHours(1), "AB", "User1"));
        Assertions.assertThrows(IllegalArgException.class, () ->
                new CarBookingRequest(100.0, localDateTime, localDateTime.plusHours(1), "123456", "User1"));
        Assertions.assertThrows(IllegalArgException.class, () ->
                new CarBookingRequest(100.0, localDateTime, localDateTime.plusHours(1), "ABCDEF", "User1"));
        Assertions.assertThrows(IllegalArgException.class, () ->
                new CarBookingRequest(100.0, localDateTime, localDateTime.plusHours(1), "123ABC", "User1"));
    }

    @Test
    void testToString() throws IllegalArgException {
        LocalDateTime localDateTime = LocalDateTime.now();
        CarBookingRequest carBookingRequest = new CarBookingRequest(100.0, localDateTime, localDateTime.plusHours(1), "ABC123", "User1");
        String verifyToString = "CarBookingRequest{" +
                "carBookingCost=" + carBookingRequest.getCarBookingCost() +
                ", fromDate=" + carBookingRequest.getFromDate() +
                ", toDate=" + carBookingRequest.getToDate() +
                ", carNumberPlate='" + carBookingRequest.getCarNumberPlate() + '\'' +
                ", carBookingUserName='" + carBookingRequest.getCarBookingUserName() + '\'' +
                '}';

        Assertions.assertEquals(verifyToString, carBookingRequest.toString());
    }

    @Test
    void testSearchCarSetMethod() throws IllegalArgException {
        LocalDateTime localDateTime = LocalDateTime.now();
        CarBookingRequest carBookingRequest = new CarBookingRequest(100.0, localDateTime, localDateTime.plusHours(1), "ABC123", "User1");
        carBookingRequest.setFromDate(localDateTime.plusHours(3));
        Assertions.assertEquals(localDateTime.plusHours(3), carBookingRequest.getFromDate());
        carBookingRequest.setToDate(localDateTime.plusHours(3));
        Assertions.assertEquals(localDateTime.plusHours(3), carBookingRequest.getToDate());
        carBookingRequest.setCarBookingUserName("User2");
        Assertions.assertEquals("User2", carBookingRequest.getCarBookingUserName());
        carBookingRequest.setCarNumberPlate("ABC123");
        Assertions.assertEquals("ABC123", carBookingRequest.getCarNumberPlate());
        carBookingRequest.setCarBookingCost(5.0);
        Assertions.assertEquals(5.0, carBookingRequest.getCarBookingCost());
    }
}

