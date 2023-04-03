package com.sprint1.AgenciaDeTurismo.Service;

import com.sprint1.AgenciaDeTurismo.DTO.RequestDto.PaymentMethodDto;
import com.sprint1.AgenciaDeTurismo.Exception.PaymentRequiredException;

public class CalcularInteres {
    public static double calculateInterest(PaymentMethodDto paymentData, double totalPrice){
        if (paymentData.getType().equalsIgnoreCase("credit")) {
            if (paymentData.getDues() > 12) {
                throw new PaymentRequiredException("Las cuotas con tarjeta de crédito no pueden ser mayor a 12");
            } else if (paymentData.getDues() <= 3) {
                return totalPrice * 0.05;
            } else if (paymentData.getDues() <= 6) {
                return totalPrice * 0.10;
            } else {
                return totalPrice * 0.15;
            }
        } else if (paymentData.getType().equalsIgnoreCase("debit")) {
            if (paymentData.getDues() != 1) {
                throw new PaymentRequiredException("Solo se permite el pago en una sola cuota");
            } else {
                return 0.0;
            }
        } else {
            throw new PaymentRequiredException("Tipo de pago no válido");
        }
    }
}
