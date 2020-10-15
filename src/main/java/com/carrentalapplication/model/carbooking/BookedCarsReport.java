package com.carrentalapplication.model.carbooking;

import com.carrentalapplication.utils.CarApplicationsConstants;
import com.carrentalapplication.utils.IllegalArgException;
import com.carrentalapplication.model.Dates;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDateTime;
import java.util.regex.Pattern;


public class BookedCarsReport extends Dates {

    private String carRentalUser;
    private String carNumberPlate;

    public BookedCarsReport(LocalDateTime fromDate, LocalDateTime toDate, String carRentalUser, String carNumberPlate) throws IllegalArgException {
        super(fromDate, toDate);
        validateCarNumberPlate(carNumberPlate);
        validateCarRentalUser(carRentalUser);
        this.carRentalUser = carRentalUser;
        this.carNumberPlate = carNumberPlate;
    }

    private BookedCarsReport() {

    }

    private void validateCarRentalUser(String carRentalUser) throws IllegalArgException {
        if (StringUtils.isEmpty(carRentalUser) || carRentalUser.length() < 2) {
            throw new IllegalArgException(CarApplicationsConstants.CAR_RENTAL_USER_ALREADY_PRESENT);
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

    public String getCarRentalUser() {
        return carRentalUser;
    }

    public void setCarRentalUser(String carRentalUser) {
        this.carRentalUser = carRentalUser;
    }


    @Override
    public String toString() {
        return "BookedCarsReport{" +
                "fromDate=" + getFromDate() +
                ", toDate=" + getToDate() +
                ", carRentalUser='" + carRentalUser + '\'' +
                ", carNumberPlate='" + carNumberPlate + '\'' +
                '}';
    }

}

