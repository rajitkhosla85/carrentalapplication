package com.carrentalapplication.service.impl;

import com.carrentalapplication.model.carrentaluser.CarRentalUser;
import com.carrentalapplication.service.CarRentalUserService;
import com.carrentalapplication.utils.IllegalArgException;
import com.carrentalapplication.repository.CarRentalUserRepository;
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

import java.util.Optional;

@ExtendWith(SpringExtension.class)
public class CarRentalUserServiceImplTest {

    @Autowired
    private CarRentalUserService carRentalUserService;
    @MockBean
    private CarRentalUserRepository carRentalUserRepository;

    @TestConfiguration
    static class CarRentalUserConfiguration {

        @Bean
        public CarRentalUserService carRentalUserService() {
            return new CarRentalUserServiceImpl();
        }

    }
    @BeforeEach()
    public void setUp() {
        CarRentalUser carRentalUser =  new CarRentalUser("User98", "Jphn", "Doe", "pas12341223232115");
        Mockito.when(carRentalUserRepository.getOne(carRentalUser.getUserName()))
                .thenReturn(carRentalUser);
        Mockito.when(carRentalUserRepository.findById(carRentalUser.getUserName()))
                .thenReturn(Optional.of(carRentalUser));
    }
    @Test
    void testBasics() throws IllegalArgException {
        CarRentalUser carRentalUser = new CarRentalUser("User99", "Jphn", "Doe", "pas12341223232115");
         carRentalUserService.createCarRentalUser(carRentalUser);
    }
    @Test
    void testCarRegsterUserAlreadyPresent() throws IllegalArgException {
        CarRentalUser carRentalUser = new CarRentalUser("User98", "Jphn", "Doe", "pas12341223232115");
        Assertions.assertThrows(IllegalArgException.class, () ->
                carRentalUserService.createCarRentalUser(carRentalUser));
    }
}
