package com.carrentalapplication.controller;

import com.carrentalapplication.model.carregisteruser.CarRegisterUser;
import com.carrentalapplication.service.CarRegisterUserService;
import com.carrentalapplication.utils.CarApplicationsConstants;
import com.carrentalapplication.utils.IllegalArgException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class CarRegisterUserController {
    private static final Logger LOGGER = LoggerFactory.getLogger(CarRegisterUserController.class);
    @Autowired
    CarRegisterUserService carRegisterUserService;

    @PostMapping("/registerusers/createCarRegisterUser")
    public CarRegisterUser createCarRegisterUser(@Valid @RequestBody CarRegisterUser newCarRegisterUser) throws IllegalArgException {
        LOGGER.debug(CarApplicationsConstants.REQUEST_RECIEVED_IN_CREATE_REGISTER_USER, newCarRegisterUser);
        return carRegisterUserService.createCarRegisterUser(newCarRegisterUser);
    }
}

