package com.sprint1.AgenciaDeTurismo.Service.Generics;



import com.sprint1.AgenciaDeTurismo.DTO.ErrorDTO;

import java.util.List;

public interface ICrudService<T, ID> {

    T saveEntity(T objectDTO);

    List<T> getAllEntities();

    T updateEntity(T objectDTO, String code);

    T getEntityByCode(String code);

    ErrorDTO deleteEntity(String code);

    ErrorDTO deleteReservaEntity(Integer ID);
}
