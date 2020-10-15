package com.carrentalapplication.repository;

import com.carrentalapplication.model.carrentaluser.CarRentalUser;
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
public class CarRentalUserRepositoryTest {

    CarRentalUser carRentalUser;
    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private CarRentalUserRepository carRentalUserRepository;

    @BeforeEach
    public void testBeforeEach() throws IllegalArgException {
        carRentalUser = new CarRentalUser("User98", "Jphn", "Doe", "pas12341223232115");
    }

    @Test
    void testBasics() {
        entityManager.persist(carRentalUser);
        entityManager.flush();

        // when
        List<CarRentalUser> carRentalUsersFound = carRentalUserRepository.findAll();

        Assertions.assertEquals(carRentalUser.getUserName(), carRentalUsersFound.get(0).getUserName());
        Assertions.assertEquals(carRentalUser.getFirstName(), carRentalUsersFound.get(0).getFirstName());
        Assertions.assertEquals(carRentalUser.getLastName(), carRentalUsersFound.get(0).getLastName());
        Assertions.assertEquals(carRentalUser.getPassword(), carRentalUsersFound.get(0).getPassword());
    }


    @Test
    void testRentalUserEmpty() {
        // when
        List<CarRentalUser> carRentalUsersFound = carRentalUserRepository.findAll();

        Assertions.assertEquals(0,carRentalUsersFound.size());
    }
    @Test
    void testFindById() {
        entityManager.persist(carRentalUser);
        entityManager.flush();

        // when
        CarRentalUser carRentalUserFound = carRentalUserRepository.findById(carRentalUser.getUserName()).get();

        Assertions.assertEquals(carRentalUser.getUserName(), carRentalUserFound.getUserName());
        Assertions.assertEquals(carRentalUser.getFirstName(), carRentalUserFound.getFirstName());
        Assertions.assertEquals(carRentalUser.getLastName(), carRentalUserFound.getLastName());
        Assertions.assertEquals(carRentalUser.getPassword(), carRentalUserFound.getPassword());
    }

    @Test
    void testCarRentalMoreThan1FindAll() {
        entityManager.persist(carRentalUser);
        entityManager.flush();
        CarRentalUser carRentalUser1 = new CarRentalUser("User99", "Jphn1", "Doe1", "pas12341223232115");
        entityManager.persist(carRentalUser1);
        entityManager.flush();
        // when
        List<CarRentalUser> carRentalUsersFound = carRentalUserRepository.findAll();
        Assertions.assertEquals(2, carRentalUsersFound.size());
        Assertions.assertEquals(carRentalUser.getUserName(), carRentalUsersFound.get(0).getUserName());
        Assertions.assertEquals(carRentalUser.getFirstName(), carRentalUsersFound.get(0).getFirstName());
        Assertions.assertEquals(carRentalUser.getLastName(), carRentalUsersFound.get(0).getLastName());
        Assertions.assertEquals(carRentalUser.getPassword(), carRentalUsersFound.get(0).getPassword());
    }

    @Test
    void testCarRentalMoreThan1FindById() {
        entityManager.persist(carRentalUser);
        entityManager.flush();
        carRentalUser = new CarRentalUser("User99", "Jphn1", "Doe1", "pas12341223232115");
        entityManager.persist(carRentalUser);
        entityManager.flush();
        // when
        // when
        CarRentalUser carRentalUserFound = carRentalUserRepository.findById(carRentalUser.getUserName()).get();

        Assertions.assertEquals(carRentalUser.getUserName(), carRentalUserFound.getUserName());
        Assertions.assertEquals(carRentalUser.getFirstName(), carRentalUserFound.getFirstName());
        Assertions.assertEquals(carRentalUser.getLastName(), carRentalUserFound.getLastName());
        Assertions.assertEquals(carRentalUser.getPassword(), carRentalUserFound.getPassword());
    }
}

