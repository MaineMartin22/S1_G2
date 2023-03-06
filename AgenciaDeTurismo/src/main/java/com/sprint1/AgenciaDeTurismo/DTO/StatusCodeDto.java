package com.sprint1.AgenciaDeTurismo.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StatusCodeDto {
    private Integer code;
    private String message;
}
