package com.sprint1.AgenciaDeTurismo.DTO.RequestDto.Hotel;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class BookingRequestDto {
    private String userName;
    private BookingDto booking;
}