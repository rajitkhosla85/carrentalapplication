package com.carrentalapplication.utils;

public class UserNotFoundException extends Exception {

    public UserNotFoundException(String id) {
        super(CarApplicationsConstants.NOT_FIND_USER + id);
    }
}
