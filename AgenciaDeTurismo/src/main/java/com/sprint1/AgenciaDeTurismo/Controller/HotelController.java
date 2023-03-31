/* package com.sprint1.AgenciaDeTurismo.Controller;

import com.sprint1.AgenciaDeTurismo.DTO.HotelDTO;
import com.sprint1.AgenciaDeTurismo.DTO.RequestDto.Hotel.BookingRequestDto;
import com.sprint1.AgenciaDeTurismo.DTO.ResponseDto.Hotel.BookingResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

@RestController
@Validated
public class HotelController {

    @Autowired
    HotelService hotelService;


    // US 0001 & 0002
    @GetMapping("/api/v1/hotels")
    // /api/v1/hotels?dateFrom=dd/mm/aaaa&dateTo=dd/mm/aaaa&destination=Puerto Iguazu

    public List<HotelDTO> hotelesDisponibles(@RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dateFrom,
                                             @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dateTo,
                                             @RequestParam(required = false) @Size(min = 3, message = "Debe ingresar un destino") String destination) {

        return hotelService.getHotelDisponibles(dateFrom, dateTo, destination);
    }

    // US 0003
    @PostMapping("/api/v1/booking")
    public BookingResponse reservaHotel(@RequestBody @Valid BookingRequestDto bookingRequestDto) {
        return hotelService.reservationHotel(bookingRequestDto);
    }

}

 */
