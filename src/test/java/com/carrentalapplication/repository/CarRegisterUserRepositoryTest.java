package com.carrentalapplication.repository;

import com.carrentalapplication.model.carregisteruser.CarRegisterUser;
import com.carrentalapplication.utils.IllegalArgException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class CarRegisterUserRepositoryTest {

    CarRegisterUser carRegisterUser;
    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private CarRegisterUserRepository carRegisterUserRepository;

    @BeforeEach
    public void testBeforeEach() throws IllegalArgException {
        carRegisterUser = new CarRegisterUser("User98", "Jphn", "Doe", "pas12341223232115");
    }

    @Test
    void testBasics() {
        entityManager.persist(carRegisterUser);
        entityManager.flush();

        // when
        List<CarRegisterUser> carRegisterUsers = carRegisterUserRepository.findAll();

        Assertions.assertEquals(carRegisterUser.getUserName(), carRegisterUsers.get(0).getUserName());
        Assertions.assertEquals(carRegisterUser.getFirstName(), carRegisterUsers.get(0).getFirstName());
        Assertions.assertEquals(carRegisterUser.getLastName(), carRegisterUsers.get(0).getLastName());
        Assertions.assertEquals(carRegisterUser.getPassword(), carRegisterUsers.get(0).getPassword());
    }


    @Test
    void testCarRegisterUserEmpty() {
        // when
        List<CarRegisterUser> carRegisterUsers = carRegisterUserRepository.findAll();

        Assertions.assertEquals(0, carRegisterUsers.size());
    }

    @Test
    void testCarRegisterUserFindById() {
        entityManager.persist(carRegisterUser);
        entityManager.flush();

        // when
        CarRegisterUser carRegisterUserFound = carRegisterUserRepository.findById(carRegisterUser.getUserName()).get();

        Assertions.assertEquals(carRegisterUser.getUserName(), carRegisterUserFound.getUserName());
        Assertions.assertEquals(carRegisterUser.getFirstName(), carRegisterUserFound.getFirstName());
        Assertions.assertEquals(carRegisterUser.getLastName(), carRegisterUserFound.getLastName());
        Assertions.assertEquals(carRegisterUser.getPassword(), carRegisterUserFound.getPassword());
    }

    @Test
    void testCarRegisterUserMoreThan1FindAll() {
        entityManager.persist(carRegisterUser);
        entityManager.flush();
        CarRegisterUser carRegisterUser1 = new CarRegisterUser("User99", "Jphn1", "Doe1", "pas12341223232115");
        entityManager.persist(carRegisterUser1);
        entityManager.flush();
        // when
        List<CarRegisterUser> carRegisterUsersFound = carRegisterUserRepository.findAll();
        Assertions.assertEquals(2, carRegisterUsersFound.size());
        Assertions.assertEquals(carRegisterUser.getUserName(), carRegisterUsersFound.get(0).getUserName());
        Assertions.assertEquals(carRegisterUser.getFirstName(), carRegisterUsersFound.get(0).getFirstName());
        Assertions.assertEquals(carRegisterUser.getLastName(), carRegisterUsersFound.get(0).getLastName());
        Assertions.assertEquals(carRegisterUser.getPassword(), carRegisterUsersFound.get(0).getPassword());
    }

    @Test
    void testCarRegisterUserMoreThan1FindById() {
        entityManager.persist(carRegisterUser);
        entityManager.flush();
        carRegisterUser = new CarRegisterUser("User99", "Jphn1", "Doe1", "pas12341223232115");
        entityManager.persist(carRegisterUser);
        entityManager.flush();
        // when
        CarRegisterUser carRegisterUserFound = carRegisterUserRepository.findById(carRegisterUser.getUserName()).get();

        Assertions.assertEquals(carRegisterUser.getUserName(), carRegisterUserFound.getUserName());
        Assertions.assertEquals(carRegisterUser.getFirstName(), carRegisterUserFound.getFirstName());
        Assertions.assertEquals(carRegisterUser.getLastName(), carRegisterUserFound.getLastName());
        Assertions.assertEquals(carRegisterUser.getPassword(), carRegisterUserFound.getPassword());
    }
}

