package com.sprint1.AgenciaDeTurismo.Exception;

public class NotFoundException extends RuntimeException{

    public NotFoundException(String message) {
        super(message);
    }
}