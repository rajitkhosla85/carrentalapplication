package com.carrentalapplication.model.cars;

import com.carrentalapplication.model.carregisteruser.CarRegisterUser;
import com.carrentalapplication.utils.CarApplicationsConstants;
import com.carrentalapplication.utils.IllegalArgException;
import com.carrentalapplication.model.Dates;

import javax.persistence.*;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Car {

    @Id
    @Pattern(regexp = CarApplicationsConstants.CAR_PLATE_NO_PATTERN, message = CarApplicationsConstants.CAR_NUMBER_PLATE_NOT_VALID)
    private String numberplate;
    @Column(name = "pricePerHour", columnDefinition = "double default 0.0")
    private Double pricePerHour;

    @Column(name = "fromDate", columnDefinition = " timestamp default ''")
    private LocalDateTime fromDate;

    @Column(name = "toDate", columnDefinition = "timestamp default ''")
    private LocalDateTime toDate;

    @ManyToOne(cascade = CascadeType.ALL)
    private CarRegisterUser carRegisterUser;

    public Car(String numberplate,CarRegisterUser carRegisterUser) {
        this.numberplate = numberplate;
        this.carRegisterUser=carRegisterUser;
    }

    private Car() {

    }

    public Car(String numberplate, Double pricePerHour, LocalDateTime fromDate, LocalDateTime toDate) throws IllegalArgException {
        Dates.validateFromToDate(fromDate, toDate);
        this.numberplate = numberplate;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.pricePerHour=pricePerHour;
    }

    public LocalDateTime getFromDate() {
        return fromDate;
    }

    public void setFromDate(LocalDateTime fromDate) {
        this.fromDate = fromDate;
    }

    public LocalDateTime getToDate() {
        return toDate;
    }

    public void setToDate(LocalDateTime toDate) {
        this.toDate = toDate;
    }

    public String getNumberplate() {
        return numberplate;
    }

    public void setNumberplate(String numberplate) {
        this.numberplate = numberplate;
    }


    public Double getPricePerHour() {
        return pricePerHour;
    }

    public void setPricePerHour(Double pricePerHour) {
        this.pricePerHour = pricePerHour;
    }

    @PrePersist
    public void prePersistValidate() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        factory.getValidator().validate(this);
    }

    @Override
    public String toString() {
        return "Car{" +
                "numberplate='" + numberplate + '\'' +
                ", pricePerHour=" + pricePerHour +
                ", fromDate=" + fromDate +
                ", toDate=" + toDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Car)) return false;
        Car car = (Car) o;
        return Objects.equals(numberplate, car.numberplate) &&
                Objects.equals(pricePerHour, car.pricePerHour) &&
                Objects.equals(fromDate, car.fromDate) &&
                Objects.equals(toDate, car.toDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numberplate, pricePerHour, fromDate, toDate);
    }
}
