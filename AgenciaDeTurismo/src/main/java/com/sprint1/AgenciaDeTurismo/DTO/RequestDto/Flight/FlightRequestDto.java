package com.sprint1.AgenciaDeTurismo.DTO.RequestDto.Flight;

import lombok.*;

import javax.validation.Valid;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class FlightRequestDto {
    private String userName;
    private @Valid FlightReservationDTO flightReservation;

}
