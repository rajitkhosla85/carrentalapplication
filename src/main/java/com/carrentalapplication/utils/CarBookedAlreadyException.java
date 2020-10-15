package com.carrentalapplication.utils;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CarBookedAlreadyException extends Exception {


    public CarBookedAlreadyException(String numberPlate) {
        super(CarApplicationsConstants.ALREADY_BOOKED_FOR_GIVEN_DATE_WITH_CAR_PLATE_NUMBER + numberPlate);
    }
}
