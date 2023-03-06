package com.sprint1.AgenciaDeTurismo.DTO.ResponseDto.Flight;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class StatusFlight {
    private Integer codeFlight;
    private String message;

}
