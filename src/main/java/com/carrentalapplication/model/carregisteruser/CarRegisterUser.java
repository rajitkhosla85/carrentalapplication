package com.carrentalapplication.model.carregisteruser;

import com.carrentalapplication.utils.CarApplicationsConstants;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
public class CarRegisterUser {

    @Id
    @Size(min = 5, message = CarApplicationsConstants.USERNAME_MINIUM_FIVE_DIGITS)
    String userName;
    @Column(name = "name")
    @Size(min = 2, message = CarApplicationsConstants.FIRST_NAME_MINIMUM_TWO_DIGITS)
    String firstName;
    @Size(min = 2, message = CarApplicationsConstants.LAST_NAME_MINIMUM_TWO_DIGITS)
    String lastName;
    @Size(min = 6, message = CarApplicationsConstants.PASS_MINIMUM_SEVEN_DIGITS)
    String password;

    public CarRegisterUser() {

    }

    public CarRegisterUser(String userName, String firstName, String lastName, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "CarRegisterUser{" +
                "userName='" + userName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CarRegisterUser)) return false;
        CarRegisterUser that = (CarRegisterUser) o;
        return Objects.equals(userName, that.userName) &&
                Objects.equals(firstName, that.firstName) &&
                Objects.equals(lastName, that.lastName) &&
                Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userName, firstName, lastName, password);
    }
}
