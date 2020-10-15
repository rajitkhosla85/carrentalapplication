package com.carrentalapplication.controller;

import com.carrentalapplication.service.CarService;
import com.carrentalapplication.utils.CarApplicationsConstants;
import com.carrentalapplication.utils.CarNotFoundException;
import com.carrentalapplication.utils.IllegalArgException;
import com.carrentalapplication.model.cars.Car;
import com.carrentalapplication.model.cars.CreateCarRequest;
import com.carrentalapplication.model.cars.SearchCar;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
public class CarController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CarController.class);
    @Autowired
    CarService carService;

    @GetMapping(value = "/cars/{plateNumber}", consumes = "application/json")
    public Car findCarById(@RequestBody String plateNumber) throws CarNotFoundException {
        LOGGER.debug(CarApplicationsConstants.REQUEST_RECIEVED_IN_CAR_FIND, plateNumber);
        return carService.findCarById(plateNumber);

    }

    @PostMapping(value = "/cars/registerCar", consumes = "application/json", produces = "application/json")
    public Car registerCar(@RequestBody CreateCarRequest createCarRequest) throws IllegalArgException {
        LOGGER.debug(CarApplicationsConstants.REQUEST_RECIEVED_IN_REGISTER_CAR, createCarRequest);
        return carService.registerCar(createCarRequest);
    }


    @PostMapping(value = "/cars/registerAvailability", consumes = "application/json")
    public Car registerAvailability(@Valid @RequestBody Car updatedCar) throws CarNotFoundException {
        LOGGER.debug(CarApplicationsConstants.REQUEST_RECIEVED_IN_REGISTER_AVAILABILITY, updatedCar);
        return carService.registerAvailability(updatedCar);
    }

    @GetMapping(value = "/cars/searchCars", consumes = "application/json")
    public List<Car> searchCars(@RequestBody SearchCar searchCar) {
        LOGGER.debug(CarApplicationsConstants.REQUEST_RECIEVED_IN_SEARCH_CAR, searchCar);
        return carService.searchCars(searchCar);
    }
}


