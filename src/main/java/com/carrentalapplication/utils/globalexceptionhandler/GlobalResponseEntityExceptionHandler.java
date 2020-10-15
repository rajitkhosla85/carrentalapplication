package com.carrentalapplication.utils.globalexceptionhandler;

import com.carrentalapplication.utils.CarBookedAlreadyException;
import com.carrentalapplication.utils.CarNotFoundException;
import com.carrentalapplication.utils.IllegalArgException;
import com.carrentalapplication.utils.UserNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    private static final Logger LOGGER_HANDLER = LoggerFactory.getLogger(GlobalResponseEntityExceptionHandler.class);

    @ExceptionHandler({IllegalArgException.class, CarNotFoundException.class, CarBookedAlreadyException.class, UserNotFoundException.class})
    public ResponseEntity<Object> handleCustomException(
            Exception ex, WebRequest request) {
        ApiError apiError =
                new ApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), ex.getMessage());
        LOGGER_HANDLER.info( ex.getLocalizedMessage());
        return new ResponseEntity(
                apiError, new HttpHeaders(), apiError.getStatus());
    }


    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ApiError apiError =
                new ApiError(HttpStatus.BAD_REQUEST, ex.getRootCause().getMessage(), ex.getRootCause().getMessage());
        LOGGER_HANDLER.info(ex.getRootCause().getMessage());
        return new ResponseEntity(
                apiError, new HttpHeaders(), apiError.getStatus());
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ApiError apiError =
                new ApiError(HttpStatus.BAD_REQUEST, ex.getBindingResult().getFieldError().getDefaultMessage(), ex.getBindingResult().getFieldError().getDefaultMessage());
        LOGGER_HANDLER.info( ex.getBindingResult().getFieldError().getDefaultMessage());
        return new ResponseEntity(
                apiError, new HttpHeaders(), apiError.getStatus());
    }
}
