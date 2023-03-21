package com.sprint1.AgenciaDeTurismo.utils.Data;

import com.sprint1.AgenciaDeTurismo.DTO.StatusCodeDto;

public class StatusCodeDTOFactory {
    public static StatusCodeDto getStatusCode(){
        return  StatusCodeDto.builder()
                .code(200)
                .message("Proceso termino satisfactoriamente")
                .build();
    }

}
