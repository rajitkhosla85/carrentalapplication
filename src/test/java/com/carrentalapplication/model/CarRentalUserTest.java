package com.carrentalapplication.model;


import com.carrentalapplication.model.carrentaluser.CarRentalUser;
import com.carrentalapplication.utils.CarApplicationsConstants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class CarRentalUserTest {

    private Validator validator;
    CarRentalUser carRentalUser;

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
         carRentalUser =  new CarRentalUser("User98", "Jphn", "Doe", "pas12341223232115");
    }

    @Test
    public void testBasicTest() {
        Set<ConstraintViolation<CarRentalUser>> violations = validator.validate(carRentalUser);
        Assertions.assertTrue(violations.isEmpty());
    }

    @Test
    public void testUserName() {

        carRentalUser.setUserName("12");
        Set<ConstraintViolation<CarRentalUser>> violations = validator.validate(carRentalUser);
        Assertions.assertEquals(1, violations.size());
        List<String> violationList= violations.stream().map(violation->violation.getMessage()).collect(Collectors.toList());
        Assertions.assertEquals(CarApplicationsConstants.USERNAME_MINIUM_FIVE_DIGITS, violationList.get(0));
    }

    @Test
    public void testFirstName() {

        carRentalUser.setFirstName("1");
        Set<ConstraintViolation<CarRentalUser>> violations = validator.validate(carRentalUser);
        Assertions.assertEquals(1, violations.size());
        List<String> violationList= violations.stream().map(violation->violation.getMessage()).collect(Collectors.toList());
        Assertions.assertEquals(CarApplicationsConstants.FIRST_NAME_MINIMUM_TWO_DIGITS, violationList.get(0));
    }

    @Test
    public void testLastName() {

        carRentalUser.setLastName("1");
        Set<ConstraintViolation<CarRentalUser>> violations = validator.validate(carRentalUser);
        Assertions.assertEquals(1, violations.size());
        List<String> violationList= violations.stream().map(violation->violation.getMessage()).collect(Collectors.toList());
        Assertions.assertEquals(CarApplicationsConstants.LAST_NAME_MINIMUM_TWO_DIGITS, violationList.get(0));
    }

    @Test
    public void testPassword() {

        carRentalUser.setPassword("234");
        Set<ConstraintViolation<CarRentalUser>> violations = validator.validate(carRentalUser);
        Assertions.assertEquals(1, violations.size());
        List<String> violationList= violations.stream().map(violation->violation.getMessage()).collect(Collectors.toList());
        Assertions.assertEquals(CarApplicationsConstants.PASS_MINIMUM_SEVEN_DIGITS, violationList.get(0));
    }
    @Test
    public void testEquals() {
        CarRentalUser carRentalUser1=new CarRentalUser("User98", "Jphn", "Doe", "pas12341223232115");
        Assertions.assertEquals(carRentalUser,carRentalUser1);
    }

    @Test
    public void testGetMethod() {
        CarRentalUser carRentalUser1=new CarRentalUser("User98", "Jphn", "Doe", "pas12341223232115");
        Assertions.assertEquals(carRentalUser.getPassword(),carRentalUser1.getPassword());
        Assertions.assertEquals(carRentalUser.getUserName(),carRentalUser1.getUserName());
        Assertions.assertEquals(carRentalUser.getFirstName(),carRentalUser1.getFirstName());
        Assertions.assertEquals(carRentalUser.getLastName(),carRentalUser1.getLastName());

    }
}
