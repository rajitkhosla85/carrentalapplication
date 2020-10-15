package com.carrentalapplication.service.impl;

import com.carrentalapplication.model.carregisteruser.CarRegisterUser;
import com.carrentalapplication.service.CarService;
import com.carrentalapplication.utils.CarNotFoundException;
import com.carrentalapplication.utils.IllegalArgException;
import com.carrentalapplication.model.cars.Car;
import com.carrentalapplication.model.cars.CreateCarRequest;
import com.carrentalapplication.model.cars.SearchCar;
import com.carrentalapplication.repository.CarRegisterUserRepository;
import com.carrentalapplication.repository.CarRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
public class CarServiceImplTest {

    Car car;
    @Autowired
    private CarService carService;
    @MockBean
    private CarRepository carRepository;
    @MockBean
    private CarRegisterUserRepository carRegisterUserRepository;

    @BeforeEach()
    public void setUp() throws IllegalArgException {
        LocalDateTime localDateTime = LocalDateTime.now();
        car = new Car("ABO129", 200.0, localDateTime, localDateTime.plusHours(3));
        Mockito.when(carRepository.getOne(car.getNumberplate()))
                .thenReturn(car);

        Mockito.when(carRepository.findById(car.getNumberplate()))
                .thenReturn(Optional.of(car));

        Car car2 = new Car("ABO130", 200.0, localDateTime, localDateTime.plusHours(3));
        Mockito.when(carRepository.findById("ABO130"))
                .thenReturn(Optional.of(car2));

        Mockito.when(carRepository.save(car))
                .thenReturn(car);
        Mockito.when(carRepository.findAllWithDatesAndMaximumPrice(car.getFromDate(), car.getToDate(), car.getPricePerHour()))
                .thenReturn(Arrays.asList(car, car2));
        CarRegisterUser carRegisterUser = new CarRegisterUser("User1", "Jphn", "Doe", "pas12341223232115");
        Mockito.when(carRegisterUserRepository.findById(carRegisterUser.getUserName())).thenReturn(Optional.of(carRegisterUser));
    }

    @Test
    void testRegisterCar() throws IllegalArgException {
        LocalDateTime localDateTime = LocalDateTime.now();
        Car car2 = new Car("ABO133", 200.0, localDateTime, localDateTime.plusHours(3));
        Mockito.when(carRepository.findById("ABO133"))
                .thenReturn(Optional.empty());
        Mockito.when(carRegisterUserRepository.findById("User91"))
                .thenReturn(Optional.empty());
        CreateCarRequest createCarRequest = new CreateCarRequest(car2.getNumberplate(), "User91");
        Assertions.assertThrows(IllegalArgException.class, () ->
                carService.registerCar(createCarRequest));
    }

    @Test
    void testRegisterCarAlreadyPersent() throws IllegalArgException {
        LocalDateTime localDateTime = LocalDateTime.now();
        Car car2 = new Car("ABO139", 200.0, localDateTime, localDateTime.plusHours(3));
        CreateCarRequest createCarRequest = new CreateCarRequest(car2.getNumberplate(), "User1");
        carService.registerCar(createCarRequest);
    }

    @Test
    void testfindCarByIdById() throws IllegalArgException, CarNotFoundException {
        LocalDateTime localDateTime = LocalDateTime.now();
        Car car2 = new Car("ABO129", 200.0, localDateTime, localDateTime.plusHours(3));
        carService.findCarById(car2.getNumberplate());
    }

    @Test
    void testfindCarByIdCarNotPresent() throws IllegalArgException {
        LocalDateTime localDateTime = LocalDateTime.now();
        Car car2 = new Car("ABO131", 200.0, localDateTime, localDateTime.plusHours(3));
        Assertions.assertThrows(CarNotFoundException.class, () ->
                carService.findCarById(car2.getNumberplate()));
    }

    @Test
    void testRegisterAvailability() throws IllegalArgException, CarNotFoundException {
        LocalDateTime localDateTime = LocalDateTime.now();
        Car car2 = new Car("ABO129", 200.0, localDateTime.plusHours(3), localDateTime.plusHours(5));
        carService.registerAvailability(car2);
    }

    @Test
    void testRegisterAvailabilityCarNotPresent() throws IllegalArgException {
        LocalDateTime localDateTime = LocalDateTime.now();
        Car car2 = new Car("ABO139", 200.0, localDateTime, localDateTime.plusHours(3));
        Assertions.assertThrows(CarNotFoundException.class, () ->
                carService.findCarById(car2.getNumberplate()));
    }

    @Test
    void testSearchCars() throws IllegalArgException, CarNotFoundException {
        LocalDateTime localDateTime = LocalDateTime.now();
        List<Car> cars = carService.searchCars(new SearchCar(car.getFromDate(), car.getToDate(), car.getPricePerHour()));
        Assertions.assertEquals(2, cars.size());
    }

    @Test
    void testSearchCarsNoCarPresent() throws IllegalArgException {
        LocalDateTime localDateTime = LocalDateTime.now();
        List<Car> cars = carService.searchCars(new SearchCar(localDateTime, localDateTime.plusHours(3), 130.0));
        Assertions.assertEquals(0, cars.size());
    }

    @TestConfiguration
    static class CarServiceConfiguration {

        @Bean
        public CarService carService() {
            return new CarServiceImpl();
        }

    }
}
