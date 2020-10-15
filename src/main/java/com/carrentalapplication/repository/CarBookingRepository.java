package com.carrentalapplication.repository;

import com.carrentalapplication.model.carbooking.CarBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CarBookingRepository extends JpaRepository<CarBooking, Integer> {


    @Query("select a from CarBooking a where ((a.fromDate >= :fromDate AND a.toDate <= :toDate) AND a.car.numberplate = :carNumberPlate)")
    CarBooking findAlreadyRegisterdBooking(
            @Param("fromDate") LocalDateTime fromDate, @Param("toDate") LocalDateTime toDate, @Param("carNumberPlate") String carNumberPlate);

    @Query("select a from CarBooking a where ((a.fromDate >= :fromDate AND a.toDate <= :toDate) AND a.car.numberplate = :carNumberPlate AND a.carRentalUser.userName = :userName)")
    List<CarBooking> searchCarsBooking(
            @Param("fromDate") LocalDateTime fromDate, @Param("toDate") LocalDateTime toDate, @Param("carNumberPlate") String carNumberPlate, @Param("userName") String userName);


    @Query("select a from CarBooking a where (a.bookingDate >= :fromDate AND a.bookingDate <= :toDate)")
    List<CarBooking> searchCarBookingForGivenDates(
            @Param("fromDate") LocalDateTime fromDate, @Param("toDate") LocalDateTime toDate);

}
