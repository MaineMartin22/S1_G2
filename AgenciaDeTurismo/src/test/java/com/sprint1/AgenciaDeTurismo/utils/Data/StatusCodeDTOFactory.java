package com.sprint1.AgenciaDeTurismo.utils.Data;

public class StatusCodeDTOFactory {
    public static StatusCodeDto getStatusCode(){
        return  StatusCodeDto.builder()
                .code(200)
                .message("Proceso termino satisfactoriamente")
                .build();
    }

}
