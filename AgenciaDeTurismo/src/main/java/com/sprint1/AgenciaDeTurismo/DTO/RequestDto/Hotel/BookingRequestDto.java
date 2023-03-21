package com.sprint1.AgenciaDeTurismo.DTO.RequestDto.Hotel;

import lombok.*;

import javax.validation.Valid;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class BookingRequestDto {
    private String userName;
    private @Valid BookingDto booking;
}