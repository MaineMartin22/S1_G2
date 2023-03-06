package com.sprint1.AgenciaDeTurismo.DTO.ResponseDto.Flight;

import com.sprint1.AgenciaDeTurismo.DTO.RequestDto.PaymentMethodDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FlightResponseDto {
    private String userName;
    private String numberFlight;
    private String origin;
    private String destiny;
    private String seatType;
    private double priceForPerson;
    private LocalDate departuraDate;
    private LocalDate returnDate;

    private PaymentMethodDto paymentMethodDto;

    private Integer seats;
}
