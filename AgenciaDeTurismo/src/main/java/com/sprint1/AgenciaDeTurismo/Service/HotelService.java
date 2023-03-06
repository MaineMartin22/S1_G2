package com.sprint1.AgenciaDeTurismo.Service;

import com.sprint1.AgenciaDeTurismo.DTO.RequestDto.Hotel.*;
import com.sprint1.AgenciaDeTurismo.DTO.ResponseDto.Hotel.BookingResponse;
import com.sprint1.AgenciaDeTurismo.DTO.ResponseDto.Hotel.BookingResponseDto;
import com.sprint1.AgenciaDeTurismo.DTO.StatusCodeDto;
import com.sprint1.AgenciaDeTurismo.Exception.SinHoteles_VuelosException;
import com.sprint1.AgenciaDeTurismo.Model.HotelModel;
import com.sprint1.AgenciaDeTurismo.Repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;


@Service
public class HotelService {
    @Autowired
    HotelRepository hotelRepository;

    // US 0001
    public List<HotelModel> findAll() {
        return hotelRepository.dataHotels();
    }

    // US 0002
    public List<HotelModel> getHotelDisponibles(String dateFrom, String dateTo, String destination) {
        return hotelRepository.getHotelDisponible(dateFrom, dateTo, destination);
    }

    // US 0003
    public BookingResponse reserva(@RequestBody BookingRequestDto bookingRequestDto) {

        if(hotelRepository.dataHotels().isEmpty()){
            throw new SinHoteles_VuelosException("No se econtraron hoteles disponibles");
        }

        BookingResponse response = new BookingResponse();
        BookingResponseDto bookingResponse = new BookingResponseDto();

        HotelModel bookHotel = hotelRepository.findHotelWhitCode(bookingRequestDto.getBooking().getHotelCode());

        if(bookHotel == null){
            throw new SinHoteles_VuelosException("No se encuentra hotel con ese código");
        }

        bookingResponse.setDateFrom(bookingRequestDto.getBooking().getDateFrom());
        bookingResponse.setDateTo(bookingRequestDto.getBooking().getDateTo());
        bookingResponse.setDestination(bookingRequestDto.getBooking().getDestination());
        bookingResponse.setHotelCode(bookingRequestDto.getBooking().getHotelCode());
        bookingResponse.setPeopleAmount(bookingRequestDto.getBooking().getPeopleAmount());
        bookingResponse.setRoomType(bookingRequestDto.getBooking().getRoomType());
        bookingResponse.setPeople(bookingRequestDto.getBooking().getPeople());

        LocalDate fechaComoLocalDateFrom = LocalDate.parse(bookingRequestDto.getBooking().getDateFrom());
        LocalDate fechaComoLocalDateTo = LocalDate.parse(bookingRequestDto.getBooking().getDateTo());

        Integer totalPrice = Math.toIntExact(ChronoUnit.DAYS.between(fechaComoLocalDateFrom, fechaComoLocalDateTo) * bookHotel.getPriceForNight());

        response.setUserName(bookingRequestDto.getUserName());
        response.setTotal(totalPrice);
        response.setBooking(bookingResponse);
        response.setStatusCode(new StatusCodeDto(200, "Proceso termino satisfactoriamente"));

        return response;
    }


}

