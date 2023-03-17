package com.sprint1.AgenciaDeTurismo.DTO.ResponseDto.Flight;

import com.sprint1.AgenciaDeTurismo.DTO.FlightDTO;
import com.sprint1.AgenciaDeTurismo.DTO.StatusCodeDto;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class FlightResponse {
    private String userName;

    private double totalNeto;

    private double totalIntereses;

    private double totalFinal;
    private FlightDTOResponse flightReservation;

    private StatusCodeDto statusCode;




}
