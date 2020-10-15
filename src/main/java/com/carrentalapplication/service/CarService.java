package com.carrentalapplication.service;

import com.carrentalapplication.model.cars.Car;
import com.carrentalapplication.model.cars.CreateCarRequest;
import com.carrentalapplication.model.cars.SearchCar;
import com.carrentalapplication.utils.CarNotFoundException;
import com.carrentalapplication.utils.IllegalArgException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CarService {
    Car findCarById(String plateNumber) throws CarNotFoundException;

    Car registerCar(CreateCarRequest createCarRequest) throws IllegalArgException;

    Car registerAvailability(Car updatedCar) throws CarNotFoundException;

    List<Car> searchCars(SearchCar searchCar);
}


