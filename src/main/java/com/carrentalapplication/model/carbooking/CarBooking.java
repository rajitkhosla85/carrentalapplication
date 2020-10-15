package com.carrentalapplication.model.carbooking;

import com.carrentalapplication.model.carrentaluser.CarRentalUser;
import com.carrentalapplication.utils.CarApplicationsConstants;
import com.carrentalapplication.model.cars.Car;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Entity
public class CarBooking implements Comparable<CarBooking> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @NotNull
    @Min(value = 1, message = CarApplicationsConstants.BOOKING_COST_GREATER_THAN_ZERO)
    private Double carBookingCost;

    @Column(name = "fromDate", columnDefinition = " timestamp default ''")
    private LocalDateTime fromDate;

    @Column(name = "toDate", columnDefinition = "timestamp default ''")
    private LocalDateTime toDate;

    @ManyToOne(cascade = CascadeType.ALL)
    private CarRentalUser carRentalUser;

    @ManyToOne(cascade = CascadeType.ALL)
    private Car car;

    @CreationTimestamp
    private LocalDateTime bookingDate;


    private CarBooking() {
    }

    public CarBooking(Double carBookingCost, LocalDateTime fromDate, LocalDateTime toDate, CarRentalUser carRentalUser, Car car) {
        this.carBookingCost = carBookingCost;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.carRentalUser = carRentalUser;
        this.car = car;
    }

    public CarBooking(CarBookingRequest carBookingRequest, CarRentalUser carRentalUser, Car car) {
        this.carBookingCost = carBookingRequest.getCarBookingCost();
        this.fromDate = carBookingRequest.getFromDate();
        this.toDate = carBookingRequest.getToDate();
        this.carRentalUser = carRentalUser;
        this.car = car;
    }

    public static Double getTotalPayments(List<CarBooking> carBookings) {
        return carBookings.stream().map(carBooking -> carBooking.getCarBookingCost()).reduce(0.0, (subtotal, element) -> subtotal + element);
    }

    public static List<Car> getCarsFromCarBookings(List<CarBooking> carBookings) {
        return carBookings.stream().map(carBooking -> carBooking.getCar()).collect(Collectors.toList());
    }

    public LocalDateTime getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(LocalDateTime bookingDate) {
        this.bookingDate = bookingDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CarBooking)) return false;
        CarBooking that = (CarBooking) o;
        return id == that.id &&
                Objects.equals(carBookingCost, that.carBookingCost) &&
                Objects.equals(fromDate, that.fromDate) &&
                Objects.equals(toDate, that.toDate) &&
                Objects.equals(carRentalUser, that.carRentalUser) &&
                Objects.equals(car, that.car) &&
                Objects.equals(bookingDate, that.bookingDate);
    }

    @Override
    public int compareTo(CarBooking other) {

        return this.getBookingDate().compareTo(other.getBookingDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, carBookingCost, fromDate, toDate, carRentalUser, car, bookingDate);
    }

    @PrePersist
    public void prePersistValidate() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        factory.getValidator().validate(this);
    }


    public Double getCarBookingCost() {
        return carBookingCost;
    }

    public void setCarBookingCost(Double carBookingCost) {
        this.carBookingCost = carBookingCost;
    }

    public LocalDateTime getFromDate() {
        return fromDate;
    }

    public LocalDateTime getToDate() {
        return toDate;
    }

    public CarRentalUser getCarRentalUser() {
        return carRentalUser;
    }

    public Car getCar() {
        return car;
    }


}
