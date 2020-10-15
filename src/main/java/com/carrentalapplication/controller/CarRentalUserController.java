package com.carrentalapplication.controller;

import com.carrentalapplication.model.carrentaluser.CarRentalUser;
import com.carrentalapplication.utils.CarApplicationsConstants;
import com.carrentalapplication.utils.IllegalArgException;
import com.carrentalapplication.service.CarRentalUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class CarRentalUserController {
    private static final Logger LOGGER = LoggerFactory.getLogger(CarRentalUserController.class);
    @Autowired
    CarRentalUserService carRentalUserService;

    @PostMapping("/rentalusers/createCarRentalUser")
    public CarRentalUser createCarRentalUser(@Valid @RequestBody CarRentalUser newCarRentalUser) throws IllegalArgException {
        LOGGER.debug(CarApplicationsConstants.REQUEST_RECIEVED_IN_CREATE_RENTAL_USER, newCarRentalUser);
        return carRentalUserService.createCarRentalUser(newCarRentalUser);
    }


}

