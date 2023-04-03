package com.sprint1.AgenciaDeTurismo.DTO.ResponseDto.Flight;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class FlightResponseDTO {
    private Integer id;
    private String userName;

    private double totalNeto;

    private double totalIntereses;

    private double totalFinal;
    private FlightDTOResponseDetails flightReservation;

}
