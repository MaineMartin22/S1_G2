package com.sprint1.AgenciaDeTurismo.DTO.RequestDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PaymentMethodDto {

//Consensuado con Scrum, requerimiento de Intereses sobre tarjeta no se realizara//
    private String type;
    private String number;

    private Integer dues;
}
