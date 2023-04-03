package com.sprint1.AgenciaDeTurismo.Service.Generics;



import com.sprint1.AgenciaDeTurismo.DTO.ErrorDTO;
import com.sprint1.AgenciaDeTurismo.DTO.ResponseDto.Hotel.BookingResponse;
import com.sprint1.AgenciaDeTurismo.Entity.BookingHotel;

import java.util.List;

public interface ICrudService<T, ID> {

    T saveEntity(T objectDTO);

    List<T> getAllEntities();


    T getEntityByCode(String code);

    ErrorDTO deleteEntity(String code);

    ErrorDTO deleteEntity(Integer ID);
}
