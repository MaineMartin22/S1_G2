package com.sprint1.AgenciaDeTurismo.DTO.RequestDto.Flight;

import com.sprint1.AgenciaDeTurismo.DTO.RequestDto.PaymentMethodDto;
import com.sprint1.AgenciaDeTurismo.DTO.RequestDto.PeopleDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class FlightDto {
     private String numberFlight;
     private String origin;
     private String destiny;
     private String seatType;
     private double priceForPerson;
     private LocalDate dateFrom;
     private LocalDate dateTo;
}
