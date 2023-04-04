package com.sprint1.AgenciaDeTurismo.utils.Data;

import com.sprint1.AgenciaDeTurismo.DTO.ErrorDTO;

import java.util.List;

public class ErrorDTOFactory {
    public static ErrorDTO accionDeEliminarBAPI() {
       return ErrorDTO.builder()
                .explanation("ELIMINACIÓN")
                .messages(List.of("Se elimino el vuelo con código BAPI-1235"))
                .build();
    }

    public static ErrorDTO deletePack1() {
        return ErrorDTO.builder()
                .explanation("ELIMINACIÓN")
                .messages(List.of("Se elimino el paquete con código PACK-001"))
                .build();
    }
}
