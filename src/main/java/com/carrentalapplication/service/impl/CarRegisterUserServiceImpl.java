package com.carrentalapplication.service.impl;

import com.carrentalapplication.model.carregisteruser.CarRegisterUser;
import com.carrentalapplication.service.CarRegisterUserService;
import com.carrentalapplication.utils.CarApplicationsConstants;
import com.carrentalapplication.utils.IllegalArgException;
import com.carrentalapplication.repository.CarRegisterUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class CarRegisterUserServiceImpl implements CarRegisterUserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CarRegisterUserServiceImpl.class);
    @Autowired
    CarRegisterUserRepository carRegisterUserRepository;

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public CarRegisterUser createCarRegisterUser(CarRegisterUser newCarRegisterUser) throws IllegalArgException {
        checkIfUserPresent(newCarRegisterUser);
        return carRegisterUserRepository.save(newCarRegisterUser);
    }

    void checkIfUserPresent(CarRegisterUser newCarRegisterUser) throws IllegalArgException {
        Optional<CarRegisterUser> searchedCar = carRegisterUserRepository.findById(newCarRegisterUser.getUserName());
        if (searchedCar.isPresent()) {
            LOGGER.info(CarApplicationsConstants.CAR_REGISTER_USER_ALREADY_PRESENT,newCarRegisterUser);
            throw new IllegalArgException(CarApplicationsConstants.CAR_REGISTER_USER_ALREADY_PRESENT);
        }
    }
}

