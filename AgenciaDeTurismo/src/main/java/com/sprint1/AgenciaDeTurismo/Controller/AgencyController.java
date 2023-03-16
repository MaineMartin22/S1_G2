package com.sprint1.AgenciaDeTurismo.Controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sprint1.AgenciaDeTurismo.DTO.HotelDTO;
import com.sprint1.AgenciaDeTurismo.DTO.RequestDto.Flight.FlightDto;
import com.sprint1.AgenciaDeTurismo.DTO.RequestDto.Flight.FlightRequestDto;
import com.sprint1.AgenciaDeTurismo.DTO.RequestDto.Hotel.BookingRequestDto;
import com.sprint1.AgenciaDeTurismo.DTO.ResponseDto.Flight.FlightResponse;
import com.sprint1.AgenciaDeTurismo.DTO.ResponseDto.Hotel.BookingResponse;
import com.sprint1.AgenciaDeTurismo.Service.FlightService;
import com.sprint1.AgenciaDeTurismo.Service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

@RestController
@Validated
public class AgencyController {

    @Autowired
    HotelService hotelService;
    @Autowired
    FlightService flightService;


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

    // US 0004 & 0005
    @GetMapping("/api/v1/flights")
    // /api/v1/flights?dateFrom=dd/mm/aaaa&dateTo=dd/mm/aaaa&origin=Buenos Aires&destination=Puerto Iguazú
    public List<FlightDto> flightAvailability(@RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dateFrom,
                                              @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dateTo,
                                              @RequestParam(required = false) @Size(min = 3, message = "Debe ingresar un origen") String origin,
                                              @RequestParam(required = false) @Size(min = 3, message = "Debe ingresar un destino") String destination) {
        return flightService.getFlightAvailability(dateFrom, dateTo, origin, destination);
    }

    // US 0006
    @PostMapping("/api/v1/flight-reservation")
    public FlightResponse flightReservation(@RequestBody @Valid FlightRequestDto flightRequestDto) {
        return flightService.reservationFlight(flightRequestDto);
    }


}

