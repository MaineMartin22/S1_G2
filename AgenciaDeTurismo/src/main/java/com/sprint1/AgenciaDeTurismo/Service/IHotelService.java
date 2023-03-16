package com.sprint1.AgenciaDeTurismo.Service;

import com.sprint1.AgenciaDeTurismo.DTO.HotelDTO;
import com.sprint1.AgenciaDeTurismo.DTO.RequestDto.Hotel.BookingRequestDto;
import com.sprint1.AgenciaDeTurismo.DTO.ResponseDto.Hotel.BookingResponse;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface IHotelService {

    List<HotelDTO> findAll();
    List<HotelDTO> getHotelDisponibles(String dateFrom, String dateTo, String destination);
    BookingResponse reservationHotel(@RequestBody BookingRequestDto bookingRequestDto);
}
