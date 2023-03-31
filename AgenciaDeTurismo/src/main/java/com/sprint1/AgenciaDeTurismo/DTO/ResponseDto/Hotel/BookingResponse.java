package com.sprint1.AgenciaDeTurismo.DTO.ResponseDto.Hotel;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class BookingResponse {
    private String userName;
    private double totalNeto;

    private double totalIntereses;

    private double totalFinal;
    private BookingResponseDto booking;
}
