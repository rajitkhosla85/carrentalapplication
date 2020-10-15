package com.carrentalapplication.model.searchcars;

import com.carrentalapplication.utils.IllegalArgException;
import com.carrentalapplication.model.cars.CreateCarRequest;
import com.carrentalapplication.model.cars.SearchCar;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

class CreateCarRequestTest {

    @Test
    void testBasics() throws IllegalArgException {

        CreateCarRequest createCarRequest=new CreateCarRequest("ABC123","User1");
        Assertions.assertEquals("ABC123",createCarRequest.getCarNumberPlate());
        Assertions.assertEquals("User1",createCarRequest.getCarRegisterUser());

    }

    @Test
    void testException() {
        LocalDateTime localDateTime=LocalDateTime.now();
        Assertions.assertThrows(IllegalArgException.class, () ->
                new CreateCarRequest("","User1"));
        Assertions.assertThrows(IllegalArgException.class, () ->
                new CreateCarRequest("123","User1"));
        Assertions.assertThrows(IllegalArgException.class, () ->
                new CreateCarRequest("123ABC","User1"));

        Assertions.assertThrows(IllegalArgException.class, () ->
                new CreateCarRequest("ABC123",""));
        Assertions.assertThrows(IllegalArgException.class, () ->
                new CreateCarRequest("ABC123","1"));
    }

    @Test
    void testToString() throws IllegalArgException {
        CreateCarRequest createCarRequest=new CreateCarRequest("ABC123","User1");
        String verifyToString="CreateCarRequest{" +
                "carNumberPlate='" + createCarRequest.getCarNumberPlate() + '\'' +
                ", carRegisterUser='" + createCarRequest.getCarRegisterUser() + '\'' +
                '}';

        Assertions.assertEquals(verifyToString, createCarRequest.toString());
    }

    @Test
    void testSearchCarSetMethod() throws IllegalArgException {
        LocalDateTime localDateTime=LocalDateTime.now();
        SearchCar searchCar=new SearchCar(localDateTime,localDateTime.plusHours(1),105.0);
        searchCar.setFromDate(localDateTime.plusHours(3));
        Assertions.assertEquals(localDateTime.plusHours(3),searchCar.getFromDate());
        searchCar.setToDate(localDateTime.plusHours(3));
        Assertions.assertEquals(localDateTime.plusHours(3),searchCar.getToDate());
        searchCar.setMaximumPricePerHour(200.0);
        Assertions.assertEquals(200.0,searchCar.getMaximumPricePerHour());
    }
}
