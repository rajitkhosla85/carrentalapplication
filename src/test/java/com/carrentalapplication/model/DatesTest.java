package com.carrentalapplication.model;

import com.carrentalapplication.utils.IllegalArgException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

class DatesTest {

    @Test
    void testDates() throws IllegalArgException {
        LocalDateTime localDateTime=LocalDateTime.now();
        Dates bookedCarsDateReport=new Dates(localDateTime,localDateTime.plusHours(1));
        Assertions.assertEquals(localDateTime,bookedCarsDateReport.getFromDate());
        Assertions.assertEquals(localDateTime.plusHours(1),bookedCarsDateReport.getToDate());

    }


    @Test
    void testDatesSetMethod() throws IllegalArgException {
        LocalDateTime localDateTime=LocalDateTime.now();
        Dates bookedCarsDateReport=new Dates(localDateTime,localDateTime.plusHours(1));
        bookedCarsDateReport.setFromDate(localDateTime.plusHours(3));
        Assertions.assertEquals(localDateTime.plusHours(3),bookedCarsDateReport.getFromDate());
        bookedCarsDateReport.setToDate(localDateTime.plusHours(3));
        Assertions.assertEquals(localDateTime.plusHours(3),bookedCarsDateReport.getToDate());
        String verifyToString="BookedCarsDateReport{" +"fromDate=" + bookedCarsDateReport.getFromDate() +", toDate=" + bookedCarsDateReport.getToDate() +'}';
        Assertions.assertEquals(verifyToString,bookedCarsDateReport.toString());
    }

    @Test
    void testFromDateToDateNegativeCase() throws IllegalArgException {
        LocalDateTime localDateTime=LocalDateTime.now();
        Assertions.assertThrows(IllegalArgException.class, () ->
                new Dates(localDateTime,localDateTime.minusHours(1)));

        Assertions.assertThrows(IllegalArgException.class, () ->
                new Dates(localDateTime,localDateTime));
    }
}

