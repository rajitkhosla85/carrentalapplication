package com.carrentalapplication.service;

import com.carrentalapplication.model.carregisteruser.CarRegisterUser;
import com.carrentalapplication.utils.IllegalArgException;
import org.springframework.stereotype.Service;

@Service
public interface CarRegisterUserService {
    CarRegisterUser createCarRegisterUser(CarRegisterUser newCarRegisterUser) throws IllegalArgException;
}
