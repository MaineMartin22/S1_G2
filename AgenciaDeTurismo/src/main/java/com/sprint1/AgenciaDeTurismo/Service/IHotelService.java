package com.sprint1.AgenciaDeTurismo.Service;

import com.sprint1.AgenciaDeTurismo.DTO.HotelDTO;
import com.sprint1.AgenciaDeTurismo.DTO.RequestDto.Hotel.BookingRequestDto;
import com.sprint1.AgenciaDeTurismo.DTO.ResponseDto.Hotel.BookingResponse;
import com.sprint1.AgenciaDeTurismo.Service.Generics.ICrudService;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDate;
import java.util.List;

public interface IHotelService extends ICrudService<HotelDTO, Integer> {

    List<HotelDTO> findAll();
    List<HotelDTO> getHotelDisponibles(LocalDate dateFrom, LocalDate dateTo, String destination);
    BookingResponse reservationHotel(@RequestBody BookingRequestDto bookingRequestDto);
}
