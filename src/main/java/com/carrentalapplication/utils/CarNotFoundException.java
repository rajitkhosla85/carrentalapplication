package com.carrentalapplication.utils;

public class CarNotFoundException extends Exception {

        public CarNotFoundException(String numberPlate) {
            super(CarApplicationsConstants.CAR_WITH_NUMBER_PLATE + numberPlate);
    }
}
