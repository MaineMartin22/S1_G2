package com.sprint1.AgenciaDeTurismo.DTO.RequestDto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PaymentMethodDto {

//Consensuado con Scrum, requerimiento de Intereses sobre tarjeta no se realizara//
    private String type;
    private String number;

    private Integer dues;
}
