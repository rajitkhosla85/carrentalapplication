package com.carrentalapplication.controller;

import com.carrentalapplication.service.CarService;
import com.carrentalapplication.utils.CarNotFoundException;
import com.carrentalapplication.utils.IllegalArgException;
import com.carrentalapplication.model.cars.Car;
import com.carrentalapplication.model.cars.CreateCarRequest;
import com.carrentalapplication.model.cars.SearchCar;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class CarRepositoryControllerTest {
    @InjectMocks
    CarController carController;

    @Mock
    CarService carService;

    @Test
    public void testFindCarById() throws IllegalArgException, CarNotFoundException {
        LocalDateTime localDateTime = LocalDateTime.now();
        Car car = new Car("ABO123", 200.0, localDateTime, localDateTime.plusHours(2));

        Mockito.when(carService.findCarById(car.getNumberplate())).thenReturn(car);

        Car carFound = carController.findCarById(car.getNumberplate());

        Assertions.assertEquals(car, carFound);

    }

    @Test
    public void testRegisterCar() throws IllegalArgException, CarNotFoundException {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        LocalDateTime localDateTime = LocalDateTime.now();
        Car car = new Car("ABO123", 200.0, localDateTime, localDateTime.plusHours(2));
        CreateCarRequest createCarRequest=new CreateCarRequest(car.getNumberplate(),"User2");
        Mockito.when(carService.registerCar(createCarRequest)).thenReturn(car);

        Car carFound = carController.registerCar(createCarRequest);

        Assertions.assertEquals(car, carFound);

    }

    @Test
    public void testRegisterAvailability() throws IllegalArgException, CarNotFoundException {
        LocalDateTime localDateTime = LocalDateTime.now();
        Car car = new Car("ABO123", 200.0, localDateTime, localDateTime.plusHours(2));

        Mockito.when(carService.registerAvailability(car)).thenReturn(car);

        Car carFound = carController.registerAvailability(car);

        Assertions.assertEquals(car, carFound);

    }

    @Test
    public void testSearchCar() throws IllegalArgException, CarNotFoundException {
        LocalDateTime localDateTime = LocalDateTime.now();
        Car car = new Car("ABO123", 200.0, localDateTime, localDateTime.plusHours(2));
        SearchCar searchCar = new SearchCar(localDateTime, localDateTime.plusHours(3), 500.0);
        Mockito.when(carService.searchCars(searchCar)).thenReturn(Arrays.asList(car));

        List<Car> carsFound = carController.searchCars(searchCar);

        Assertions.assertEquals(car, carsFound.get(0));
        Assertions.assertEquals(1, carsFound.size());

    }
}
