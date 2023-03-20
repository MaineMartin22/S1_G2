package com.sprint1.AgenciaDeTurismo.DTO.RequestDto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PaymentMethodDto {

    private String type;
    private String number;

    private Integer dues;
}
