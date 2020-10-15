package com.carrentalapplication.controller;

import com.carrentalapplication.model.carregisteruser.CarRegisterUser;
import com.carrentalapplication.service.CarRegisterUserService;
import com.carrentalapplication.utils.IllegalArgException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CarRegisterUserControllerTest {
    @InjectMocks
    CarRegisterUserController carRegisterUserController;

    @Mock
    CarRegisterUserService carRegisterUserService;

    @Test
    public void testRegisterUserController() throws IllegalArgException {
        CarRegisterUser carRegisterUser = new CarRegisterUser("User98", "Jphn", "Doe", "pas12341223232115");

        Mockito.when(carRegisterUserService.createCarRegisterUser(carRegisterUser)).thenReturn(carRegisterUser);

        CarRegisterUser carRegisterUser1 = carRegisterUserService.createCarRegisterUser(carRegisterUser);

        Assertions.assertEquals(carRegisterUser, carRegisterUser1);

    }
}
