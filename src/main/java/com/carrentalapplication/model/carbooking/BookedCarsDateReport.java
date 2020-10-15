package com.carrentalapplication.model.carbooking;

import com.carrentalapplication.utils.IllegalArgException;
import com.carrentalapplication.model.Dates;

import java.time.LocalDateTime;

public class BookedCarsDateReport extends Dates {
    public BookedCarsDateReport(LocalDateTime fromDate, LocalDateTime toDate) throws IllegalArgException {
        super(fromDate, toDate);
    }

    private BookedCarsDateReport() {

    }
}


