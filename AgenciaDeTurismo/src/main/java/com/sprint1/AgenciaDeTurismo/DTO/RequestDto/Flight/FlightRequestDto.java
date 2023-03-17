package com.sprint1.AgenciaDeTurismo.DTO.RequestDto.Flight;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter


public class FlightRequestDto {
    private String userName;
    private @Valid FlightReservationDTO flightReservation;

}
