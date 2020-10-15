package com.carrentalapplication.model.searchcars;

import com.carrentalapplication.utils.IllegalArgException;
import com.carrentalapplication.model.cars.SearchCar;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

class SearchCarTest {

    @Test
    void testBasics() throws IllegalArgException {

        LocalDateTime localDateTime=LocalDateTime.now();
        SearchCar searchCarhCar=new SearchCar(localDateTime,localDateTime.plusHours(1),100.0);
        Assertions.assertEquals(localDateTime,searchCarhCar.getFromDate());
        Assertions.assertEquals(localDateTime.plusHours(1),searchCarhCar.getToDate());
        Assertions.assertEquals(100.0,searchCarhCar.getMaximumPricePerHour());

    }

    @Test
    void testMaximumPricePerHour() {
        LocalDateTime localDateTime=LocalDateTime.now();
        Assertions.assertThrows(IllegalArgException.class, () ->
                new SearchCar(localDateTime,localDateTime.minusHours(1),-10.0));
        Assertions.assertThrows(IllegalArgException.class, () ->
                new SearchCar(localDateTime,localDateTime.minusHours(1),0.0));

    }

    @Test
    void testToString() throws IllegalArgException {
        LocalDateTime localDateTime=LocalDateTime.now();
        SearchCar searchCar=new SearchCar(localDateTime,localDateTime.plusHours(1),100.0);
        String verifyToString="SearchCar{" +
                "maximumPricePerHour=" + searchCar.getMaximumPricePerHour() +
                ", fromDate=" + searchCar.getFromDate() +
                ", toDate=" +searchCar. getToDate() +
                '}';

        Assertions.assertEquals(verifyToString, searchCar.toString());
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
