package com.sprint1.AgenciaDeTurismo.utils.Data;

import com.sprint1.AgenciaDeTurismo.DTO.DestinoMasSolicitado;
import com.sprint1.AgenciaDeTurismo.DTO.ErrorDTO;

import java.util.List;

public class DestinoMasSolicitadoDTOFactory {
    public static DestinoMasSolicitado destinoMasSolicitadoHoteles() {
        return DestinoMasSolicitado.builder()
                .message("El destino mas solicitado es Puerto Iguazú")
                .count(1)
                .build();
    }
}
