package com.testapplication;

import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

public class TestApplication {
    private static final Logger LOGGER = LoggerFactory.getLogger(TestApplication.class);
    static WebClient WEBCLIENT = WebClient.create("http://localhost:9090");
    private static int CONCURRENT_TESTING_MIN = 1;

    public static void main(String[] args) {
        LocalDateTime localDateTime = LocalDateTime.now();
        while (localDateTime.plusMinutes(CONCURRENT_TESTING_MIN).isAfter(LocalDateTime.now())) {
            String carNumberPlate = RandomStringUtils.random(3, true, false) + RandomStringUtils.random(3, false, true);
            String registerUser = RandomStringUtils.random(5, true, false);
            Flux.merge(createRegisterUserMono(registerUser), createRentalUserMono(registerUser), registerCars(carNumberPlate, registerUser), registerAvailability(carNumberPlate), createSearchCarMono(), createCarBookingMono(carNumberPlate, registerUser)).subscribe(LOGGER::debug);

        }

    }

    private static Mono<String> createRegisterUserMono(String username) {
        String query = "{\"userName\":\"" + username + "\",\"firstName\":\"Jphn\",\"lastName\":\"123\",\"password\":\"pas12345\"}";
        return WEBCLIENT.method(HttpMethod.POST).uri("/registerusers/createCarRegisterUser").contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromPublisher(Mono.just(query
                ), String.class)).retrieve().bodyToMono(String.class);
    }

    private static Mono<String> createSearchCarMono() {
        return WEBCLIENT.method(HttpMethod.GET).uri("/cars/searchCars").contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromPublisher(Mono.just("{\"maximumPricePerHour\":\"300.0\",\"fromDate\":\"2020-09-04T18:06:22.000Z\",\"toDate\":\"2021-09-15T18:06:22.000Z\"}"
                ), String.class)).retrieve().bodyToMono(String.class);
    }

    private static Mono<String> createCarBookingMono(String carNumberPlate, String carRentalUser) {
        LocalDateTime localDateTime = LocalDateTime.now();
        String query = "{\"carBookingCost\":\"300.0\",\"fromDate\":\"" + localDateTime + "\",\"toDate\":\"" + localDateTime.plusMinutes(1) + "\",\"carNumberPlate\":\"" + carNumberPlate + "\",\"carBookingUserName\":\"" + carRentalUser + "\"}";
        return WEBCLIENT.method(HttpMethod.POST).uri("/bookings/createCarBooking").contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromPublisher(Mono.just(query
                ), String.class)).retrieve().bodyToMono(String.class);
    }

    private static Mono<String> registerCars(String carNumberPlate, String userName) {
        String query = "{\"carNumberPlate\":\"" + carNumberPlate + "\",\"carRegisterUser\":\"" + userName + "\"}";
        return WEBCLIENT.method(HttpMethod.POST).uri("/cars/registerCar").contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromPublisher(Mono.just(query
                ), String.class)).retrieve().bodyToMono(String.class);

    }

    private static Mono<String> registerAvailability(String carNumberPlate) {
        String queryRegisterAvailability = "{\"numberplate\":\"" + carNumberPlate + "\",\"pricePerHour\":\"29.0\",\"fromDate\":\"2020-09-04T18:06:22.000Z\",\"toDate\":\"2030-09-15T18:06:22.000Z\"}";
        return WEBCLIENT.method(HttpMethod.POST).uri("/cars/registerAvailability").contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromPublisher(Mono.just(queryRegisterAvailability
                ), String.class)).retrieve().bodyToMono(String.class);

    }

    private static Mono<String> createRentalUserMono(String username) {
        String query = "{\"userName\":\"" + username + "\",\"firstName\":\"Jphn\",\"lastName\":\"123\",\"password\":\"pas12345\"}";
        return WEBCLIENT.method(HttpMethod.POST).uri("/rentalusers/createCarRentalUser").contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromPublisher(Mono.just(query
                ), String.class)).retrieve().bodyToMono(String.class);
    }
}
