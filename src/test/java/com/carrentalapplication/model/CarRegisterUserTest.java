package com.carrentalapplication.model;


import com.carrentalapplication.model.carregisteruser.CarRegisterUser;
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

public class CarRegisterUserTest {

    private Validator validator;
    CarRegisterUser carRegisterUser;

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
         carRegisterUser =  new CarRegisterUser("User98", "Jphn", "Doe", "pas12341223232115");
    }

    @Test
    public void testBasicTest() {
        Set<ConstraintViolation<CarRegisterUser>> violations = validator.validate(carRegisterUser);
        Assertions.assertTrue(violations.isEmpty());
    }

    @Test
    public void testUserName() {

        carRegisterUser.setUserName("12");
        Set<ConstraintViolation<CarRegisterUser>> violations = validator.validate(carRegisterUser);
        Assertions.assertEquals(1, violations.size());
        List<String> violationList= violations.stream().map(violation->violation.getMessage()).collect(Collectors.toList());
        Assertions.assertEquals(CarApplicationsConstants.USERNAME_MINIUM_FIVE_DIGITS, violationList.get(0));
    }

    @Test
    public void testFirstName() {

        carRegisterUser.setFirstName("1");
        Set<ConstraintViolation<CarRegisterUser>> violations = validator.validate(carRegisterUser);
        Assertions.assertEquals(1, violations.size());
        List<String> violationList= violations.stream().map(violation->violation.getMessage()).collect(Collectors.toList());
        Assertions.assertEquals(CarApplicationsConstants.FIRST_NAME_MINIMUM_TWO_DIGITS, violationList.get(0));
    }

    @Test
    public void testLastName() {

        carRegisterUser.setLastName("1");
        Set<ConstraintViolation<CarRegisterUser>> violations = validator.validate(carRegisterUser);
        Assertions.assertEquals(1, violations.size());
        List<String> violationList= violations.stream().map(violation->violation.getMessage()).collect(Collectors.toList());
        Assertions.assertEquals(CarApplicationsConstants.LAST_NAME_MINIMUM_TWO_DIGITS, violationList.get(0));
    }

    @Test
    public void testPassword() {

        carRegisterUser.setPassword("234");
        Set<ConstraintViolation<CarRegisterUser>> violations = validator.validate(carRegisterUser);
        Assertions.assertEquals(1, violations.size());
        List<String> violationList= violations.stream().map(violation->violation.getMessage()).collect(Collectors.toList());
        Assertions.assertEquals(CarApplicationsConstants.PASS_MINIMUM_SEVEN_DIGITS, violationList.get(0));
    }
    @Test
    public void testEquals() {
        CarRegisterUser carRegisterUser1=new CarRegisterUser("User98", "Jphn", "Doe", "pas12341223232115");
        Assertions.assertEquals(carRegisterUser,carRegisterUser1);
    }

    @Test
    public void testGetMethod() {
        CarRegisterUser carRegisterUser1=new CarRegisterUser("User98", "Jphn", "Doe", "pas12341223232115");
        Assertions.assertEquals(carRegisterUser.getPassword(),carRegisterUser1.getPassword());
        Assertions.assertEquals(carRegisterUser.getUserName(),carRegisterUser1.getUserName());
        Assertions.assertEquals(carRegisterUser.getFirstName(),carRegisterUser1.getFirstName());
        Assertions.assertEquals(carRegisterUser.getLastName(),carRegisterUser1.getLastName());

    }
}
