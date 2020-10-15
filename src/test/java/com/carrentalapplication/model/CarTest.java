package com.carrentalapplication.model;


import com.carrentalapplication.utils.CarApplicationsConstants;
import com.carrentalapplication.utils.IllegalArgException;
import com.carrentalapplication.model.cars.Car;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class CarTest {

    private Validator validator;
    Car car;

    @BeforeEach
    public void setUp() throws IllegalArgException {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
        LocalDateTime localDateTime = LocalDateTime.now();
         car = new Car("ABO123", 200.0, localDateTime, localDateTime.plusHours(2));
    }

    @Test
    public void testBasicTest() {
        Set<ConstraintViolation<Car>> violations = validator.validate(car);
        Assertions.assertTrue(violations.isEmpty());
    }

    @Test
    public void testNumberPlateWrongPattern() {

        car.setNumberplate("123ABC");
        Set<ConstraintViolation<Car>> violations = validator.validate(car);
        Assertions.assertEquals(1, violations.size());
        List<String> violationList= violations.stream().map(violation->violation.getMessage()).collect(Collectors.toList());
        Assertions.assertEquals(CarApplicationsConstants.CAR_NUMBER_PLATE_NOT_VALID, violationList.get(0));
    }

    @Test
    public void testNumberPlateLessCharacters() {
        car.setNumberplate("ABC12");
        Set<ConstraintViolation<Car>> violations = validator.validate(car);
        Assertions.assertEquals(1, violations.size());
        List<String> violationList= violations.stream().map(violation->violation.getMessage()).collect(Collectors.toList());
        Assertions.assertEquals(CarApplicationsConstants.CAR_NUMBER_PLATE_NOT_VALID, violationList.get(0));
    }
    @Test
    public void testEquals() throws IllegalArgException {
        Car newCar=new Car("ABO123", 200.0, car.getFromDate(), car.getToDate());
        Assertions.assertEquals(car,newCar);
    }


}
