package com.sprint1.AgenciaDeTurismo.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalHandler {

    @ExceptionHandler(PaymentRequiredException.class)
    public ResponseEntity<String> handlerRuntime(PaymentRequiredException exception){
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.PAYMENT_REQUIRED);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> handlerRuntime(NotFoundException exception){
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<String> handlerRuntime(BadRequestException exception){
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }
}