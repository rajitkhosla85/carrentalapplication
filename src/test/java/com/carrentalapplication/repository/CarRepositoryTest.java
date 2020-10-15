package com.carrentalapplication.repository;

import com.carrentalapplication.utils.IllegalArgException;
import com.carrentalapplication.model.cars.Car;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.List;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class CarRepositoryTest {

    Car car;
    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private CarRepository carRepository;

    @BeforeEach
    public void testBeforeEach() throws IllegalArgException {
        LocalDateTime localDateTime = LocalDateTime.now();
        car = new Car("ABO129", 200.0, localDateTime, localDateTime.plusHours(3));
    }


    @Test
    void testBasics() {
        entityManager.persist(car);
        entityManager.flush();

        // when
        List<Car> carsFound = carRepository.findAll();
        Assertions.assertEquals(1, carsFound.size());
        Assertions.assertEquals(car.getNumberplate(), carsFound.get(0).getNumberplate());
        Assertions.assertEquals(car.getFromDate(), carsFound.get(0).getFromDate());
        Assertions.assertEquals(car.getToDate(), carsFound.get(0).getToDate());
        Assertions.assertEquals(car.getPricePerHour(), carsFound.get(0).getPricePerHour());

    }

    @Test
    void testCarsEmpty() {
        // when
        List<Car> carsFound = carRepository.findAll();
        Assertions.assertEquals(0, carsFound.size());

    }

    @Test
    void testCarFindById() {
        entityManager.persist(car);
        entityManager.flush();

        // when
        Car carFound = carRepository.findById(car.getNumberplate()).get();

        Assertions.assertEquals(car.getNumberplate(), carFound.getNumberplate());
        Assertions.assertEquals(car.getFromDate(), carFound.getFromDate());
        Assertions.assertEquals(car.getToDate(), carFound.getToDate());
        Assertions.assertEquals(car.getPricePerHour(), carFound.getPricePerHour());

    }

    @Test
    void testCarsMorThan1() throws IllegalArgException {
        entityManager.persist(car);
        entityManager.flush();
        LocalDateTime localDateTime = LocalDateTime.now();
        Car car1 = new Car("ABO130", 200.0, localDateTime, localDateTime.plusHours(3));
        entityManager.persist(car1);
        entityManager.flush();
        // when
        List<Car> carsFound = carRepository.findAll();
        Assertions.assertEquals(2, carsFound.size());
        Assertions.assertEquals(car.getNumberplate(), carsFound.get(0).getNumberplate());
        Assertions.assertEquals(car.getFromDate(), carsFound.get(0).getFromDate());
        Assertions.assertEquals(car.getToDate(), carsFound.get(0).getToDate());
        Assertions.assertEquals(car.getPricePerHour(), carsFound.get(0).getPricePerHour());

    }

    @Test
    void testCarsMorThan1FindById() throws IllegalArgException {
        entityManager.persist(car);
        entityManager.flush();
        LocalDateTime localDateTime = LocalDateTime.now();
        Car car1 = new Car("ABO130", 200.0, localDateTime, localDateTime.plusHours(3));
        entityManager.persist(car1);
        entityManager.flush();
        // when
        Car carFound = carRepository.findById(car.getNumberplate()).get();

        Assertions.assertEquals(car.getNumberplate(), carFound.getNumberplate());
        Assertions.assertEquals(car.getFromDate(), carFound.getFromDate());
        Assertions.assertEquals(car.getToDate(), carFound.getToDate());
        Assertions.assertEquals(car.getPricePerHour(), carFound.getPricePerHour());

    }

    @Test
    void testSearchCar() throws IllegalArgException {
        entityManager.persist(car);
        entityManager.flush();
        LocalDateTime localDateTime = LocalDateTime.now();
        Car car1 = new Car("ABO130", 150.0, localDateTime, localDateTime.plusHours(3));
        entityManager.persist(car1);
        entityManager.flush();
        // when
        List<Car> carsFound = carRepository.findAllWithDatesAndMaximumPrice(localDateTime.minusDays(1), localDateTime.plusDays(2), 180.0);

        Assertions.assertEquals(1, carsFound.size());
        Assertions.assertEquals(car1.getNumberplate(), carsFound.get(0).getNumberplate());
        Assertions.assertEquals(car1.getFromDate(), carsFound.get(0).getFromDate());
        Assertions.assertEquals(car1.getToDate(), carsFound.get(0).getToDate());
        Assertions.assertEquals(car1.getPricePerHour(), carsFound.get(0).getPricePerHour());

        carsFound = carRepository.findAllWithDatesAndMaximumPrice(localDateTime.minusDays(1), localDateTime.plusDays(2), 200.0);
        Assertions.assertEquals(2, carsFound.size());
        Assertions.assertEquals(car, carsFound.get(0));
        Assertions.assertEquals(car1, carsFound.get(1));

        carsFound = carRepository.findAllWithDatesAndMaximumPrice(localDateTime.plusDays(5), localDateTime.plusDays(8), 200.0);
        Assertions.assertEquals(0, carsFound.size());

    }

}
