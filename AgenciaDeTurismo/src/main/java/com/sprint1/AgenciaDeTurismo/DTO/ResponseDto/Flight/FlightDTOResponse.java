package com.sprint1.AgenciaDeTurismo.DTO.ResponseDto.Flight;

import com.sprint1.AgenciaDeTurismo.DTO.RequestDto.PaymentMethodDto;
import com.sprint1.AgenciaDeTurismo.DTO.RequestDto.PeopleDto;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class FlightDTOResponse {
    private LocalDate dateFrom;
    private LocalDate dateTo;
    private String origin;
    private String destination;
    private String flightNumber;
    private Integer seats;
    private String seatType;
    private List<PeopleDto> peopleDto;
    private PaymentMethodDto paymentMethodDto;
}