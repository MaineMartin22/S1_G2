package com.sprint1.AgenciaDeTurismo.DTO;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class StatusCodeDto {
    private Integer code;
    private String message;
}
