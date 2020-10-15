package com.carrentalapplication.model.searchcars;

import com.carrentalapplication.utils.IllegalArgException;
import com.carrentalapplication.model.carbooking.BookedCarsPerHourReportResult;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

class BookedCarsPerHourReportResultTest {

    @Test
    void testBasics() throws IllegalArgException {

        LocalDateTime localDateTime=LocalDateTime.now();
        BookedCarsPerHourReportResult bookedCarsDateReport=new BookedCarsPerHourReportResult(localDateTime,localDateTime.plusHours(1),5);
        Assertions.assertEquals(localDateTime,bookedCarsDateReport.getFromDate());
        Assertions.assertEquals(localDateTime.plusHours(1),bookedCarsDateReport.getToDate());
        Assertions.assertEquals(5,bookedCarsDateReport.getNoOfCarBooked());

    }

    @Test
    void testNoOfCarNegative() {
        LocalDateTime localDateTime=LocalDateTime.now();
        Assertions.assertThrows(IllegalArgException.class, () ->
                new BookedCarsPerHourReportResult(localDateTime,localDateTime.plusHours(1),-5));

    }

    @Test
    void testToString() throws IllegalArgException {
        LocalDateTime localDateTime=LocalDateTime.now();
        BookedCarsPerHourReportResult bookedCarsDateReport=new BookedCarsPerHourReportResult(localDateTime,localDateTime.plusHours(1),5);
        String verifyToString="BookedCarsPerHourReportResult{" +
                "fromDate=" + bookedCarsDateReport.getFromDate() +
                ", toDate=" + bookedCarsDateReport.getToDate() +
                ", noOfCarBooked=" + bookedCarsDateReport.getNoOfCarBooked() +
                '}';
        Assertions.assertEquals(verifyToString, bookedCarsDateReport.toString());
    }

    @Test
    void testBookedCarsPerHourSetMethod() throws IllegalArgException {
        LocalDateTime localDateTime=LocalDateTime.now();
        BookedCarsPerHourReportResult bookedCarsDateReport=new BookedCarsPerHourReportResult(localDateTime,localDateTime.plusHours(1),5);
        bookedCarsDateReport.setFromDate(localDateTime.plusHours(3));
        Assertions.assertEquals(localDateTime.plusHours(3),bookedCarsDateReport.getFromDate());
        bookedCarsDateReport.setToDate(localDateTime.plusHours(3));
        Assertions.assertEquals(localDateTime.plusHours(3),bookedCarsDateReport.getToDate());

        bookedCarsDateReport.setNoOfCarBooked(10);
        Assertions.assertEquals(10,bookedCarsDateReport.getNoOfCarBooked());
    }
}
