package com.carrentalapplication.service.impl;

import com.carrentalapplication.model.carregisteruser.CarRegisterUser;
import com.carrentalapplication.service.CarService;
import com.carrentalapplication.utils.CarApplicationsConstants;
import com.carrentalapplication.utils.CarNotFoundException;
import com.carrentalapplication.utils.IllegalArgException;
import com.carrentalapplication.model.cars.Car;
import com.carrentalapplication.model.cars.CreateCarRequest;
import com.carrentalapplication.model.cars.SearchCar;
import com.carrentalapplication.repository.CarRegisterUserRepository;
import com.carrentalapplication.repository.CarRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CarServiceImpl implements CarService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CarRegisterUserServiceImpl.class);
    @Autowired
    CarRepository carRepository;
    @Autowired
    CarRegisterUserRepository carRegisterUserRepository;

    @Override
    @Transactional(readOnly = true)
    public Car findCarById(String plateNumber) throws CarNotFoundException {
        return carRepository.findById(plateNumber)
                .orElseThrow(() -> {
                    LOGGER.info(CarApplicationsConstants.CAR_WITH_NUMBER_PLATE + plateNumber);
                    return new CarNotFoundException(plateNumber);
                });

    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Car registerCar(CreateCarRequest createCarRequest) throws IllegalArgException {
        checkIfCarPresent(createCarRequest.getCarNumberPlate());
        CarRegisterUser carRegisterUser = checkIfRegisterUserNotPresent(createCarRequest.getCarRegisterUser());
        Car newCar = new Car(createCarRequest.getCarNumberPlate(), carRegisterUser);
        return carRepository.save(newCar);
    }

    private void checkIfCarPresent(String numberPlate) throws IllegalArgException {
        Optional<Car> searchedCar = carRepository.findById(numberPlate);
        if (searchedCar.isPresent()) {
            LOGGER.info(CarApplicationsConstants.CAR_ALREADY_REGISTERED, numberPlate);
            throw new IllegalArgException(CarApplicationsConstants.CAR_ALREADY_REGISTERED);
        }

    }

    private CarRegisterUser checkIfRegisterUserNotPresent(String registerUser) throws IllegalArgException {
        Optional<CarRegisterUser> searchedRegisterdUser = carRegisterUserRepository.findById(registerUser);
        if (!searchedRegisterdUser.isPresent()) {
            LOGGER.info(CarApplicationsConstants.REGISTER_USER_NOT_PRESENT, registerUser);
            throw new IllegalArgException(CarApplicationsConstants.REGISTER_USER_NOT_PRESENT);
        }
        return searchedRegisterdUser.get();

    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Car registerAvailability(Car updatedCar) throws CarNotFoundException {
        return carRepository.findById(updatedCar.getNumberplate())
                .map(car -> {
                    car.setPricePerHour(updatedCar.getPricePerHour());
                    car.setFromDate(updatedCar.getFromDate());
                    car.setToDate(updatedCar.getToDate());
                    return carRepository.save(car);
                })
                .orElseThrow(() -> {
                    LOGGER.info(CarApplicationsConstants.CAR_ALREADY_REGISTERED + updatedCar);
                    return new CarNotFoundException(updatedCar.getNumberplate());
                });
    }

    @Override
    @Transactional(readOnly = true)
    public List<Car> searchCars(SearchCar searchCar) {
        return carRepository.findAllWithDatesAndMaximumPrice(searchCar.getFromDate(), searchCar.getToDate(), searchCar.getMaximumPricePerHour());

    }
}


