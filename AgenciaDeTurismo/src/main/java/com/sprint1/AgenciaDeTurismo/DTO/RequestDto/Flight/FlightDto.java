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
     private String dateFrom;
     private String dateTo;
     private String origin;
     private String destination;
     private String flightNumber;
     private Integer seats;
     private String seatType;
     private PeopleDto people;
     private PaymentMethodDto paymentMethod;
}
