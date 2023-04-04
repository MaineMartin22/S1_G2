package com.sprint1.AgenciaDeTurismo.Service;

import com.sprint1.AgenciaDeTurismo.DTO.HotelDTO;
import com.sprint1.AgenciaDeTurismo.DTO.RequestDto.Hotel.BookingRequestDto;
import com.sprint1.AgenciaDeTurismo.DTO.ResponseDto.Hotel.BookingResponseDTO;
import com.sprint1.AgenciaDeTurismo.Service.Generics.ICrudService;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface IHotelService extends ICrudService<HotelDTO, Integer> {
    List<BookingResponseDTO> getAllEntitiesResponse();

    List<HotelDTO> getAllEntitiesByCity(String city);
    BookingResponseDTO reservationHotel(@RequestBody BookingRequestDto bookingRequestDto);

    BookingResponseDTO updateReservaEntity(@RequestBody BookingResponseDTO bookingResponseDTO, Integer id);

    List<BookingResponseDTO> getAllEntitiesForPrice(Double desde, Double hasta);


}
