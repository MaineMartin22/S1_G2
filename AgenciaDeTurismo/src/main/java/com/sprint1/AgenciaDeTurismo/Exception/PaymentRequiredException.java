
package com.sprint1.AgenciaDeTurismo.Exception;

public class PaymentRequiredException extends RuntimeException{

    public PaymentRequiredException(String message) {
        super(message);
    }
}