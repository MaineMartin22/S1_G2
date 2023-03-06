package com.sprint1.AgenciaDeTurismo.DTO.ResponseDto.Flight;

import com.sprint1.AgenciaDeTurismo.DTO.RequestDto.PaymentMethodDto;
import com.sprint1.AgenciaDeTurismo.DTO.StatusCodeDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class FlightResponse {
    private String userName;

    private Integer total;

    private FlightResponseDto flightReservation;

    private StatusCodeDto statusCode;




}
