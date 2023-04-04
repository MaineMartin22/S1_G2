package com.sprint1.AgenciaDeTurismo.utils.Data;

import com.sprint1.AgenciaDeTurismo.DTO.ErrorDTO;
import com.sprint1.AgenciaDeTurismo.DTO.GananciasDTO;

import java.util.List;

public class GananciasDTOFactory {
    public static GananciasDTO gananciasHoteles() {
        return GananciasDTO.builder()
                .message("El total recaudado en las reservas de hoteles es: ")
                .count(220500.0)
                .reservationCount("En total hubo 1 reservas")
                .companyProfit("Las ganancias finales de la empresa son: 33075.0")
                .build();
    }
    public static GananciasDTO gananciasVuelos() {
        return GananciasDTO.builder()
                .message("El total recaudado en las reservas de vuelos es: ")
                .count(14300.0)
                .reservationCount("En total hubo 1 reservas")
                .companyProfit("Las ganancias finales de la empresa son: 2145.0")
                .build();
    }
}



