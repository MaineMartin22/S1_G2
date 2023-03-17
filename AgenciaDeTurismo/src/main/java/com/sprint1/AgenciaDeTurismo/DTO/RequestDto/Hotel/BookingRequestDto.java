package com.sprint1.AgenciaDeTurismo.DTO.RequestDto.Hotel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter

public class BookingRequestDto {
    private String userName;
    private @Valid BookingDto booking;
}