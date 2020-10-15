package com.carrentalapplication.repository;

import com.carrentalapplication.model.carrentaluser.CarRentalUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRentalUserRepository extends JpaRepository<CarRentalUser, String> {
}
