package com.sprint1.AgenciaDeTurismo.Service;

import com.sprint1.AgenciaDeTurismo.DTO.RequestDto.Flight.FlightRequestDto;
import com.sprint1.AgenciaDeTurismo.DTO.RequestDto.Hotel.BookingRequestDto;
import com.sprint1.AgenciaDeTurismo.DTO.ResponseDto.Flight.FlightResponse;
import com.sprint1.AgenciaDeTurismo.DTO.ResponseDto.Hotel.BookingResponse;
import com.sprint1.AgenciaDeTurismo.Model.HotelModel;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface IHotelService {

    List<HotelModel> findAll();
    List<HotelModel> getHotelDisponibles(String dateFrom, String dateTo, String destination);
    BookingResponse reservationHotel(@RequestBody BookingRequestDto bookingRequestDto);
}
