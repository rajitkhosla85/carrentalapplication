package com.carrentalapplication.model.searchcars;

import com.carrentalapplication.model.carbooking.BookedCarsDateReport;
import com.carrentalapplication.utils.IllegalArgException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

class BookedCarsDateReportTest {

    @Test
    void testCarDateReport() throws IllegalArgException {
        LocalDateTime localDateTime=LocalDateTime.now();
        BookedCarsDateReport bookedCarsDateReport=new BookedCarsDateReport(localDateTime,localDateTime.plusHours(1));
        Assertions.assertEquals(localDateTime,bookedCarsDateReport.getFromDate());
        Assertions.assertEquals(localDateTime.plusHours(1),bookedCarsDateReport.getToDate());

    }


    @Test
    void testCarDateSetMethod() throws IllegalArgException{
        LocalDateTime localDateTime=LocalDateTime.now();
        BookedCarsDateReport bookedCarsDateReport=new BookedCarsDateReport(localDateTime,localDateTime.plusHours(1));
        bookedCarsDateReport.setFromDate(localDateTime.plusHours(3));
        Assertions.assertEquals(localDateTime.plusHours(3),bookedCarsDateReport.getFromDate());
        bookedCarsDateReport.setToDate(localDateTime.plusHours(3));
        Assertions.assertEquals(localDateTime.plusHours(3),bookedCarsDateReport.getToDate());
        String verifyToString="BookedCarsDateReport{" +"fromDate=" + bookedCarsDateReport.getFromDate() +", toDate=" + bookedCarsDateReport.getToDate() +'}';
        Assertions.assertEquals(verifyToString,bookedCarsDateReport.toString());
    }

}

