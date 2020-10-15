package com.carrentalapplication.model.cars;

import com.carrentalapplication.utils.CarApplicationsConstants;
import com.carrentalapplication.utils.IllegalArgException;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;
import java.util.regex.Pattern;


public class CreateCarRequest {
    private String carNumberPlate;

    private String carRegisterUser;

    public CreateCarRequest(String carNumberPlate, String carRegisterUser) throws IllegalArgException {

        validateCarNumberPlate(carNumberPlate);
        validateCarRegisterUser(carRegisterUser);
        this.carNumberPlate = carNumberPlate;
        this.carRegisterUser = carRegisterUser;
    }

    private CreateCarRequest() {

    }

    private void validateCarRegisterUser(String carBookingUserName) throws IllegalArgException {
        if (StringUtils.isEmpty(carBookingUserName) || carBookingUserName.length() < 2) {
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

    public String getCarRegisterUser() {
        return carRegisterUser;
    }

    public void setCarRegisterUser(String carRegisterUser) {
        this.carRegisterUser = carRegisterUser;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CreateCarRequest)) return false;
        CreateCarRequest that = (CreateCarRequest) o;
        return Objects.equals(carNumberPlate, that.carNumberPlate) &&
                Objects.equals(carRegisterUser, that.carRegisterUser);
    }

    @Override
    public int hashCode() {
        return Objects.hash(carNumberPlate, carRegisterUser);
    }

    @Override
    public String toString() {
        return "CreateCarRequest{" +
                "carNumberPlate='" + carNumberPlate + '\'' +
                ", carRegisterUser='" + carRegisterUser + '\'' +
                '}';
    }
}
