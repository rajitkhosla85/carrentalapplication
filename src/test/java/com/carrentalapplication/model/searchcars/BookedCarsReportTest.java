package com.carrentalapplication.model.searchcars;

import com.carrentalapplication.utils.IllegalArgException;
import com.carrentalapplication.model.carbooking.BookedCarsReport;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

class BookedCarsReportTest {

    @Test
    void testBasics() throws IllegalArgException {

        LocalDateTime localDateTime=LocalDateTime.now();
        BookedCarsReport bookedCarsReport=new BookedCarsReport(localDateTime,localDateTime.plusHours(1),"User1","ABC123");
        Assertions.assertEquals(localDateTime,bookedCarsReport.getFromDate());
        Assertions.assertEquals(localDateTime.plusHours(1),bookedCarsReport.getToDate());
        Assertions.assertEquals("User1",bookedCarsReport.getCarRentalUser());
        Assertions.assertEquals("ABC123",bookedCarsReport.getCarNumberPlate());

    }

    @Test
    void testCarRentalUsers() {
        LocalDateTime localDateTime=LocalDateTime.now();
        Assertions.assertThrows(IllegalArgException.class, () ->
                new BookedCarsReport(localDateTime,localDateTime.plusHours(1),"","ABC123"));
        Assertions.assertThrows(IllegalArgException.class, () ->
                new BookedCarsReport(localDateTime,localDateTime.minusHours(1),"User1","ABC123"));
        Assertions.assertThrows(IllegalArgException.class, () ->
                new BookedCarsReport(localDateTime,localDateTime.plusHours(1),"U","ABC123"));
    }
    @Test
    void testCarNumberPlate() {
        LocalDateTime localDateTime=LocalDateTime.now();
        Assertions.assertThrows(IllegalArgException.class, () ->
                new BookedCarsReport(localDateTime,localDateTime.plusHours(1),"User1",""));
        Assertions.assertThrows(IllegalArgException.class, () ->
                new BookedCarsReport(localDateTime,localDateTime.minusHours(1),"User1","AB"));
        Assertions.assertThrows(IllegalArgException.class, () ->
                new BookedCarsReport(localDateTime,localDateTime.plusHours(1),"U","123456"));
        Assertions.assertThrows(IllegalArgException.class, () ->
                new BookedCarsReport(localDateTime,localDateTime.plusHours(1),"U","ABCDEF"));
        Assertions.assertThrows(IllegalArgException.class, () ->
                new BookedCarsReport(localDateTime,localDateTime.plusHours(1),"U","123ABC"));
    }
    @Test
    void testToString() throws IllegalArgException {
        LocalDateTime localDateTime=LocalDateTime.now();
        BookedCarsReport bookedCarsReport=new BookedCarsReport(localDateTime,localDateTime.plusHours(1),"User1","ABC123");
        String verifyToString="BookedCarsReport{" +
                "fromDate=" + bookedCarsReport.getFromDate() +
                ", toDate=" + bookedCarsReport.getToDate() +
                ", carRentalUser='" + bookedCarsReport.getCarRentalUser()+ '\'' +
                ", carNumberPlate='" + bookedCarsReport.getCarNumberPlate() + '\'' +
                '}';

        Assertions.assertEquals(verifyToString, bookedCarsReport.toString());
    }

    @Test
    void testSearchCarSetMethod() throws IllegalArgException {
        LocalDateTime localDateTime=LocalDateTime.now();
        BookedCarsReport bookedCarsReport=new BookedCarsReport(localDateTime,localDateTime.plusHours(1),"User1","ABC123");
        bookedCarsReport.setFromDate(localDateTime.plusHours(3));
        Assertions.assertEquals(localDateTime.plusHours(3),bookedCarsReport.getFromDate());
        bookedCarsReport.setToDate(localDateTime.plusHours(3));
        Assertions.assertEquals(localDateTime.plusHours(3),bookedCarsReport.getToDate());
        bookedCarsReport.setCarRentalUser("User2");
        Assertions.assertEquals("User2",bookedCarsReport.getCarRentalUser());
        bookedCarsReport.setCarNumberPlate("ABC123");
        Assertions.assertEquals("ABC123",bookedCarsReport.getCarNumberPlate());
    }
}

