package com.carrentalapplication.repository;

import com.carrentalapplication.model.carrentaluser.CarRentalUser;
import com.carrentalapplication.utils.IllegalArgException;
import com.carrentalapplication.model.cars.Car;
import com.carrentalapplication.model.carbooking.CarBooking;
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
public class CarBookingRepositoryTest {

    CarBooking carBooking;
    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private CarBookingRepository carBookingRepository;

    @BeforeEach
    public void testBeforeEach() throws IllegalArgException {
        LocalDateTime localDateTime = LocalDateTime.now();
        carBooking = new CarBooking(30.0, localDateTime, localDateTime.plusHours(1), new CarRentalUser("User98", "Jphn", "Doe", "pas12341223232115"), new Car("ABO129", 200.0, localDateTime, localDateTime.plusHours(3)));
    }

    @Test
    void testBasics() {
        entityManager.persist(carBooking);
        entityManager.flush();

        // when
        List<CarBooking> carBookingsFound = carBookingRepository.findAll();

        Assertions.assertEquals(carBooking.getBookingDate(), carBookingsFound.get(0).getBookingDate());
        Assertions.assertEquals(1, carBookingsFound.size());
        Assertions.assertEquals(carBooking.getCar(), carBookingsFound.get(0).getCar());
        Assertions.assertEquals(carBooking.getCarRentalUser(), carBookingsFound.get(0).getCarRentalUser());
        Assertions.assertEquals(carBooking.getCarBookingCost(), carBookingsFound.get(0).getCarBookingCost());
        Assertions.assertEquals(carBooking.getFromDate(), carBookingsFound.get(0).getFromDate());
        Assertions.assertEquals(carBooking.getToDate(), carBookingsFound.get(0).getToDate());
    }

    @Test
    void testFindCarByDateNumberPlate() {
        entityManager.persist(carBooking);
        entityManager.flush();

        // when
        CarBooking carBookingFound = carBookingRepository.findAlreadyRegisterdBooking(carBooking.getFromDate().minusMinutes(1), carBooking.getToDate().plusMinutes(1), carBooking.getCar().getNumberplate());

        Assertions.assertEquals(carBooking.getCar(), carBookingFound.getCar());
        Assertions.assertEquals(carBooking.getCarRentalUser(), carBookingFound.getCarRentalUser());
        Assertions.assertEquals(carBooking.getCarBookingCost(), carBookingFound.getCarBookingCost());
        Assertions.assertEquals(carBooking.getFromDate(), carBookingFound.getFromDate());
        Assertions.assertEquals(carBooking.getToDate(), carBookingFound.getToDate());
    }

    @Test
    void testFindCarByDateNumberPlateEmpty() {
        // when
        CarBooking carBookingFound = carBookingRepository.findAlreadyRegisterdBooking(carBooking.getFromDate().minusNanos(2), carBooking.getToDate().minusMinutes(1), carBooking.getCar().getNumberplate());

        Assertions.assertNull(carBookingFound);
    }

    @Test
    void testFindCarByDateNumberPlateNegative() {
        entityManager.persist(carBooking);
        entityManager.flush();

        // when
        CarBooking carBookingFound = carBookingRepository.findAlreadyRegisterdBooking(carBooking.getFromDate().minusNanos(2), carBooking.getToDate().minusMinutes(1), carBooking.getCar().getNumberplate());

        Assertions.assertNull(carBookingFound);
    }

    @Test
    void testSearchBookingMoreThan1() throws IllegalArgException {
        entityManager.persist(carBooking);
        entityManager.flush();
        LocalDateTime localDateTime = LocalDateTime.now();
        CarBooking carBooking2 = new CarBooking(30.0, localDateTime.minusHours(2), localDateTime.minusHours(1), new CarRentalUser("User81", "Jphn", "Doe", "pas12341223232115"), new Car("ABO130", 200.0, localDateTime, localDateTime.plusHours(3)));
        entityManager.persist(carBooking2);
        entityManager.flush();
        // when
        List<CarBooking> carBookingsFound = carBookingRepository.findAll();

        Assertions.assertEquals(2, carBookingsFound.size());
        Assertions.assertEquals(carBooking.getCar(), carBookingsFound.get(0).getCar());
        Assertions.assertEquals(carBooking.getCarRentalUser(), carBookingsFound.get(0).getCarRentalUser());
        Assertions.assertEquals(carBooking.getCarBookingCost(), carBookingsFound.get(0).getCarBookingCost());
        Assertions.assertEquals(carBooking.getFromDate(), carBookingsFound.get(0).getFromDate());
        Assertions.assertEquals(carBooking.getToDate(), carBookingsFound.get(0).getToDate());
    }

    @Test
    void testFindCarByDates() {
        entityManager.persist(carBooking);
        entityManager.flush();

        List<CarBooking> carBookingsFound = carBookingRepository.searchCarBookingForGivenDates(carBooking.getFromDate(), carBooking.getToDate());
        carBookingRepository.findAll();
        Assertions.assertEquals(1, carBookingsFound.size());
        Assertions.assertEquals(carBooking.getCar(), carBookingsFound.get(0).getCar());
        Assertions.assertEquals(carBooking.getCarRentalUser(), carBookingsFound.get(0).getCarRentalUser());
        Assertions.assertEquals(carBooking.getCarBookingCost(), carBookingsFound.get(0).getCarBookingCost());
        Assertions.assertEquals(carBooking.getFromDate(), carBookingsFound.get(0).getFromDate());
        Assertions.assertEquals(carBooking.getToDate(), carBookingsFound.get(0).getToDate());
    }

    @Test
    void testFindCarByDatesEmpty() {
        List<CarBooking> carBookingsFound = carBookingRepository.searchCarBookingForGivenDates(carBooking.getFromDate(), carBooking.getToDate());
        Assertions.assertEquals(0, carBookingsFound.size());

    }

    @Test
    void testFindCarByDatesMoreThan1() throws IllegalArgException {
        entityManager.persist(carBooking);
        entityManager.flush();
        LocalDateTime localDateTime = LocalDateTime.now();
        CarBooking carBooking2 = new CarBooking(30.0, localDateTime.minusHours(2), localDateTime.minusHours(1), new CarRentalUser("User81", "Jphn", "Doe", "pas12341223232115"), new Car("ABO130", 200.0, localDateTime, localDateTime.plusHours(3)));
        entityManager.persist(carBooking2);
        entityManager.flush();
        List<CarBooking> carBookingsFound = carBookingRepository.searchCarBookingForGivenDates(carBooking.getFromDate().minusHours(5), carBooking.getToDate().plusHours(3));
        Assertions.assertEquals(2, carBookingsFound.size());
        Assertions.assertEquals(carBooking.getCar(), carBookingsFound.get(0).getCar());
        Assertions.assertEquals(carBooking.getCarRentalUser(), carBookingsFound.get(0).getCarRentalUser());
        Assertions.assertEquals(carBooking.getCarBookingCost(), carBookingsFound.get(0).getCarBookingCost());
        Assertions.assertEquals(carBooking.getFromDate(), carBookingsFound.get(0).getFromDate());
        Assertions.assertEquals(carBooking.getToDate(), carBookingsFound.get(0).getToDate());
    }

    @Test
    void testFindCarByDateNumberPlateAndUSer() {
        entityManager.persist(carBooking);
        entityManager.flush();

        // when
        List<CarBooking> carBookingsFound = carBookingRepository.searchCarsBooking(carBooking.getFromDate().minusMinutes(1), carBooking.getToDate().plusMinutes(1), carBooking.getCar().getNumberplate(), carBooking.getCarRentalUser().getUserName());

        Assertions.assertEquals(carBooking.getCar(), carBookingsFound.get(0).getCar());
        Assertions.assertEquals(carBooking.getCarRentalUser(), carBookingsFound.get(0).getCarRentalUser());
        Assertions.assertEquals(carBooking.getCarBookingCost(), carBookingsFound.get(0).getCarBookingCost());
        Assertions.assertEquals(carBooking.getFromDate(), carBookingsFound.get(0).getFromDate());
        Assertions.assertEquals(carBooking.getToDate(), carBookingsFound.get(0).getToDate());
    }

    @Test
    void testFindCarByDateNumberPlateAndUserEmpty() {
        List<CarBooking> carBookingsFound = carBookingRepository.searchCarsBooking(carBooking.getFromDate().minusMinutes(1), carBooking.getToDate().plusMinutes(1), carBooking.getCar().getNumberplate(), carBooking.getCarRentalUser().getUserName());
        Assertions.assertEquals(0, carBookingsFound.size());

    }

    @Test
    void testFindCarByDateNumberPlateAndUserMoreThan1() throws IllegalArgException {
        entityManager.persist(carBooking);
        entityManager.flush();
        LocalDateTime localDateTime = LocalDateTime.now();
        CarBooking carBooking2 = new CarBooking(30.0, localDateTime.minusHours(2), localDateTime.minusHours(1), new CarRentalUser("User81", "Jphn", "Doe", "pas12341223232115"), new Car("ABO130", 200.0, localDateTime, localDateTime.plusHours(3)));
        entityManager.persist(carBooking2);
        entityManager.flush();
        // when
        List<CarBooking> carBookingsFound = carBookingRepository.searchCarsBooking(carBooking.getFromDate().minusHours(4), carBooking.getToDate().plusHours(3), carBooking.getCar().getNumberplate(), carBooking.getCarRentalUser().getUserName());
        Assertions.assertEquals(1, carBookingsFound.size());
        Assertions.assertEquals(carBooking.getCar(), carBookingsFound.get(0).getCar());
        Assertions.assertEquals(carBooking.getCarRentalUser(), carBookingsFound.get(0).getCarRentalUser());
        Assertions.assertEquals(carBooking.getCarBookingCost(), carBookingsFound.get(0).getCarBookingCost());
        Assertions.assertEquals(carBooking.getFromDate(), carBookingsFound.get(0).getFromDate());
        Assertions.assertEquals(carBooking.getToDate(), carBookingsFound.get(0).getToDate());
    }
}

