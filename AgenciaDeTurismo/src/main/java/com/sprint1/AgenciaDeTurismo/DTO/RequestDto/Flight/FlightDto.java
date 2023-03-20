package com.sprint1.AgenciaDeTurismo.DTO.RequestDto.Flight;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sprint1.AgenciaDeTurismo.DTO.RequestDto.PaymentMethodDto;
import com.sprint1.AgenciaDeTurismo.DTO.RequestDto.PeopleDto;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class FlightDto {
     private String numberFlight;
     private String origin;
     private String destiny;
     private String seatType;
     private double priceForPerson;
     @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
     private LocalDate dateFrom;
     @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
     private LocalDate dateTo;
}
