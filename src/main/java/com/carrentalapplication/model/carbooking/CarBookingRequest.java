package com.carrentalapplication.model.carbooking;

import com.carrentalapplication.utils.CarApplicationsConstants;
import com.carrentalapplication.utils.IllegalArgException;
import com.carrentalapplication.model.Dates;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDateTime;
import java.util.regex.Pattern;


public class CarBookingRequest extends Dates {

    private Double carBookingCost;
    private String carNumberPlate;
    private String carBookingUserName;

    private CarBookingRequest() {
    }

    public CarBookingRequest(Double carBookingCost, LocalDateTime fromDate, LocalDateTime toDate, String carNumberPlate, String carBookingUserName) throws IllegalArgException {
        super(fromDate, toDate);
        validateCarNumberPlate(carNumberPlate);
        validateBookingUser(carBookingUserName);
        validateCarBookingCost(carBookingCost);
        this.carBookingCost = carBookingCost;
        this.carNumberPlate = carNumberPlate;
        this.carBookingUserName = carBookingUserName;
    }

    private void validateBookingUser(String carBookingUserName) throws IllegalArgException {
        if (StringUtils.isEmpty(carBookingUserName) || carBookingUserName.length() < 2) {
            throw new IllegalArgException(CarApplicationsConstants.CAR_RENTAL_USER_ALREADY_PRESENT);
        }
    }

    private void validateCarBookingCost(Double carBookingCost) throws IllegalArgException {
        if (carBookingCost <= 0.0) {
            throw new IllegalArgException(CarApplicationsConstants.CAR_BOOKING_COST_GREATER_ZERO);
        }
    }

    private void validateCarNumberPlate(String carNumberPlate) throws IllegalArgException {
        if (StringUtils.isEmpty(carNumberPlate)) {
            throw new IllegalArgException(CarApplicationsConstants.CAR_NUMBER_PLATE_NOT_VALID);
        }
        Pattern pattern = Pattern.compile(CarApplicationsConstants.CAR_PLATE_NO_PATTERN);
        if (!pattern.matcher(carNumberPlate).find()) {
            throw new IllegalArgException(CarApplicationsConstants.CAR_NUMBER_PLATE_NOT_VALID);
        }
    }

    public String getCarNumberPlate() {
        return carNumberPlate;
    }

    public void setCarNumberPlate(String carNumberPlate) {
        this.carNumberPlate = carNumberPlate;
    }

    public String getCarBookingUserName() {
        return carBookingUserName;
    }

    public void setCarBookingUserName(String carBookingUserName) {
        this.carBookingUserName = carBookingUserName;
    }

    public Double getCarBookingCost() {
        return carBookingCost;
    }

    public void setCarBookingCost(Double carBookingCost) {
        this.carBookingCost = carBookingCost;
    }


    @Override
    public String toString() {
        return "CarBookingRequest{" +
                "carBookingCost=" + carBookingCost +
                ", fromDate=" + getFromDate() +
                ", toDate=" + getToDate() +
                ", carNumberPlate='" + carNumberPlate + '\'' +
                ", carBookingUserName='" + carBookingUserName + '\'' +
                '}';
    }
}
