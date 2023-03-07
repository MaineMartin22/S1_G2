package com.sprint1.AgenciaDeTurismo.Exception;

public class BadRequestException extends RuntimeException{

    public BadRequestException(String message) {
        super(message);
    }
}