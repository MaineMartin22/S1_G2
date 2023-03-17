package com.sprint1.AgenciaDeTurismo.DTO.RequestDto.Flight;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class FlightRequestDto {
    private String userName;
    private FlightReservationDTO flightReservation;

}
