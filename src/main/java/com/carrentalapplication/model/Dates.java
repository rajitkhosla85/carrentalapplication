package com.carrentalapplication.model;

import com.carrentalapplication.utils.CarApplicationsConstants;
import com.carrentalapplication.utils.IllegalArgException;

import java.time.LocalDateTime;


public class Dates {
    private LocalDateTime fromDate;

    private LocalDateTime toDate;

    public Dates(LocalDateTime fromDate, LocalDateTime toDate) throws IllegalArgException {
        validateFromToDate(fromDate, toDate);
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    public Dates() {
    }

    public static void validateFromToDate(LocalDateTime fromDate, LocalDateTime toDate) throws IllegalArgException {
        if (fromDate.isAfter(toDate.minusMinutes(1))) {
            throw new IllegalArgException(CarApplicationsConstants.FROM_DATE_GREATER_THAN_TO_DATE);
        }
    }

    public LocalDateTime getFromDate() {
        return fromDate;
    }

    public void setFromDate(LocalDateTime fromDate) {
        this.fromDate = fromDate;
    }

    public LocalDateTime getToDate() {
        return toDate;
    }

    public void setToDate(LocalDateTime toDate) {
        this.toDate = toDate;
    }

    @Override
    public String toString() {
        return "BookedCarsDateReport{" +
                "fromDate=" + fromDate +
                ", toDate=" + toDate +
                '}';
    }
}

