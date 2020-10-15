package com.carrentalapplication.repository;

import com.carrentalapplication.model.cars.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, String> {

    @Query("select a from Car a where ((a.fromDate >= :fromDate AND a.toDate <= :toDate) AND a.pricePerHour <= :pricePerHour)")
    List<Car> findAllWithDatesAndMaximumPrice(
            @Param("fromDate") LocalDateTime fromDate, @Param("toDate") LocalDateTime toDate, @Param("pricePerHour") Double pricePerHour);
}
