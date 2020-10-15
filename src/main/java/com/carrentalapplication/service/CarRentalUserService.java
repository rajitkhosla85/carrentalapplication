package com.carrentalapplication.service;

import com.carrentalapplication.model.carrentaluser.CarRentalUser;
import com.carrentalapplication.utils.IllegalArgException;
import org.springframework.stereotype.Service;

@Service
public interface CarRentalUserService {
    CarRentalUser createCarRentalUser(CarRentalUser newCarRentalUser) throws IllegalArgException;
}

