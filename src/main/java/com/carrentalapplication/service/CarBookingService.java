package com.carrentalapplication.service;

import com.carrentalapplication.model.cars.Car;
import com.carrentalapplication.model.carbooking.CarBooking;
import com.carrentalapplication.model.carbooking.CarBookingRequest;
import com.carrentalapplication.model.carbooking.BookedCarsDateReport;
import com.carrentalapplication.model.carbooking.BookedCarsPerHourReportResult;
import com.carrentalapplication.model.carbooking.BookedCarsReport;
import com.carrentalapplication.utils.CarBookedAlreadyException;
import com.carrentalapplication.utils.CarNotFoundException;
import com.carrentalapplication.utils.IllegalArgException;
import com.carrentalapplication.utils.UserNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CarBookingService {
    CarBooking createBooking(CarBookingRequest newCarBookingRequest) throws CarBookedAlreadyException, UserNotFoundException, CarNotFoundException;

    List<Car> searchBookedCars(BookedCarsReport bookedCarsReport);

    Double searchTotalPayment(BookedCarsDateReport bookedCarsReport);

    List<BookedCarsPerHourReportResult> searchBookedCarPerHour(BookedCarsDateReport bookedCarsReport) throws IllegalArgException;
}
