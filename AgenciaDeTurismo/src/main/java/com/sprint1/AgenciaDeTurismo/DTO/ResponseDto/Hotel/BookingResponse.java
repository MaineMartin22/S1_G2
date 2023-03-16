package com.sprint1.AgenciaDeTurismo.DTO.ResponseDto.Hotel;

import com.sprint1.AgenciaDeTurismo.DTO.StatusCodeDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BookingResponse {
    private String userName;
    private double totalNeto;

    private double totalIntereses;

    private double totalFinal;
    private BookingResponseDto booking;
    private StatusCodeDto statusCode;
}
