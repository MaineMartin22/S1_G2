package com.sprint1.AgenciaDeTurismo.utils.Data;

import com.sprint1.AgenciaDeTurismo.DTO.RequestDto.PaymentMethodDto;

public class PaymentMethodDTOFactory {
    public static PaymentMethodDto getPaymentDebit(){
        return PaymentMethodDto.builder()
                .type("Debit")
                .dues(1)
                .number("2349-1249-1094-5831")
                .build();
    }
    public static PaymentMethodDto getPaymentCreditRefused(){
        return PaymentMethodDto.builder()
                .type("Credit")
                .dues(13)
                .number("9012-4583-6327-1746")
                .build();
    }
    public static PaymentMethodDto getPaymentCreditThreeDues(){
        return PaymentMethodDto.builder()
                .type("Credit")
                .dues(3)
                .number("3478-2385-2163-7549")
                .build();
    }
    public static PaymentMethodDto getPaymentCreditSixDues(){
        return PaymentMethodDto.builder()
                .type("Credit")
                .dues(6)
                .number("8672-3295-1576-4185")
                .build();
    }
    public static PaymentMethodDto getPaymentCreditTwelveDues(){
        return PaymentMethodDto.builder()
                .type("Credit")
                .dues(12)
                .number("5321-7856-3409-1267")
                .build();
    }

}
