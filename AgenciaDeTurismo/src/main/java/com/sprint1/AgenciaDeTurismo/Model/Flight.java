package com.sprint1.AgenciaDeTurismo.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
public class Flight {
    private String nroVuelo;
    private  String origen;
    private String destino;
    private String typeAsiento;
    private String priceForPerson;
    private String fechaIda;
    private String fechaVuelta;
}
