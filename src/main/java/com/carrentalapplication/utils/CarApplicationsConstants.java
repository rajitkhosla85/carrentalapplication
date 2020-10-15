package com.carrentalapplication.utils;

public final class CarApplicationsConstants {


    public static final String CAR_ALREADY_REGISTERED = "Car Already Registered";
    public static final String CAR_REGISTER_USER_ALREADY_PRESENT = "Car Register User Already Present";
    public static final String CAR_RENTAL_USER_ALREADY_PRESENT = "Car Rental User Already Present";
    public static final String CAR_BOOKING_COST_GREATER_ZERO = "Car Booking cost should be greater than zero";
    public static final String CAR_NUMBER_PLATE_NOT_VALID = "Car Number Plate not valid";
    public static final String FROM_DATE_GREATER_THAN_TO_DATE = "From Date should be smaller than To date";
    public static final String NO_OF_BOOKED_CAR_GREATER_THAN_ZERO = "No Of Booked Cars should be greater than 0";
    public static final String MAXIMUM_PRICE_GREATER_THAN_ZERO = "Maximum price of the car should be greater than 0";
    public static final String CAR_PLATE_NO_PATTERN = "[a-zA-Z]{3}[0-9]{3}";
    public static final String BOOKING_COST_GREATER_THAN_ZERO = "Car Booking cost should be greater than zero";
    public static final String USERNAME_MINIUM_FIVE_DIGITS = "Username should be minimum 5 digits";
    public static final String FIRST_NAME_MINIMUM_TWO_DIGITS = "First Name should be minimum 2 digits";
    public static final String LAST_NAME_MINIMUM_TWO_DIGITS = "Last Name should be minimum 2 digits";
    public static final String PASS_MINIMUM_SEVEN_DIGITS = "Password should be minimum 7 digits";
    public static final String REGISTER_USER_NOT_PRESENT = "Car Register User not present";

    //Logs
    public static final String REQUEST_RECIEVED_IN_CREATE_CAR_BOOKING = "Request Recieved in createCarBooking-";
    public static final String REQUEST_RECIEVED_IN_SEARCH_BOOKED_CAR_PER_HOUR = "Request Recieved in searchBookedCarPerHour";
    public static final String REQUEST_RECIEVED_IN_SEARCH_TOTAL_PAYMENT = "Request Recieved in searchTotalPayment-";
    public static final String REQUEST_RECIEVED_IN_SEARCH_BOOKED_CARS = "Request Recieved in searchBookedCars-";
    public static final String REQUEST_RECIEVED_IN_CAR_FIND = "Request Recieved in findById-";
    public static final String REQUEST_RECIEVED_IN_SEARCH_CAR = "Request Recieved in searchCar-";
    public static final String REQUEST_RECIEVED_IN_REGISTER_CAR = "Request Recieved in registercar-";
    public static final String REQUEST_RECIEVED_IN_REGISTER_AVAILABILITY = "Request Recieved in registeravaialbility-";
    public static final String REQUEST_RECIEVED_IN_CREATE_REGISTER_USER = "Request Recieved in createregisteruser-";
    public static final String REQUEST_RECIEVED_IN_CREATE_RENTAL_USER = "Request Recieved in createrentaluser-";
    public static final String ALREADY_BOOKED_FOR_GIVEN_DATE_WITH_CAR_PLATE_NUMBER = "Car is already Booked for given date with car plate number ";
    public static final String CAR_WITH_NUMBER_PLATE = "Could not find Car with Number Plate- ";
    public static final String NOT_FIND_USER = "Could not find User ";

    private CarApplicationsConstants() {

    }
}

