package com.carrentalapplication.controller;

import com.carrentalapplication.model.carrentaluser.CarRentalUser;
import com.carrentalapplication.service.CarRentalUserService;
import com.carrentalapplication.utils.IllegalArgException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CarRentalUserControllerTest {
    @InjectMocks
    CarRentalUserController carRentalUserController;

    @Mock
    CarRentalUserService carRentalUserService;

    @Test
    public void testRentalUserController() throws IllegalArgException {
        CarRentalUser carRentalUser = new CarRentalUser("User98", "Jphn", "Doe", "pas12341223232115");

        Mockito.when(carRentalUserService.createCarRentalUser(carRentalUser)).thenReturn(carRentalUser);

        CarRentalUser carRentalUser1 = carRentalUserController.createCarRentalUser(carRentalUser);

        Assertions.assertEquals(carRentalUser, carRentalUser1);

    }
}
