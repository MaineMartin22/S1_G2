package com.sprint1.AgenciaDeTurismo.Controller;

import com.sprint1.AgenciaDeTurismo.DTO.RequestDto.Flight.FlightRequestDto;
import com.sprint1.AgenciaDeTurismo.DTO.RequestDto.Hotel.BookingRequestDto;
import com.sprint1.AgenciaDeTurismo.DTO.ResponseDto.Flight.FlightResponse;
import com.sprint1.AgenciaDeTurismo.DTO.ResponseDto.Hotel.BookingResponse;
import com.sprint1.AgenciaDeTurismo.Model.FlightModel;
import com.sprint1.AgenciaDeTurismo.Model.HotelModel;
import com.sprint1.AgenciaDeTurismo.Service.AgencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AgencyController {

    @Autowired
    AgencyService agencyService;

    @GetMapping("/api/v1/hotels")
    public List<HotelModel> get() {
        return agencyService.findAll();
    }

    @GetMapping("/api/v1/hotel")
    // /api/v1/hotels?dateFrom=dd/mm/aaaa&dateTo=dd/mm/aaaa&destination=Puerto Iguazu
    public List<HotelModel> hotelesDisponibles(@RequestParam String dateFrom, @RequestParam String dateTo, @RequestParam String destination) {
        return agencyService.getHotelDisponibles(dateFrom, dateTo, destination);
    }

    @GetMapping("/api/v1/flights")
    public List<FlightModel> getFlights() {
        return agencyService.getFlight();
    }

    @GetMapping("/api/v1/flight")
    // /api/v1/flights?dateFrom=dd/mm/aaaa&dateTo=dd/mm/aaaa&origin=Buenos Aires&destination=Puerto Iguazú
    public List<FlightModel> flightAvailability(@RequestParam String dateFrom, @RequestParam String dateTo, @RequestParam String origin, @RequestParam String destination) {
        return agencyService.getFlightAvailability(dateFrom, dateTo, origin, destination);
    }

    @PostMapping("/api/v1/booking")
    public BookingResponse reservaHotel(@RequestBody BookingRequestDto bookingRequestDto) {
        return agencyService.reserva(bookingRequestDto);
    }
    @PostMapping("/api/v1/flight-reservation")
    public FlightResponse flightReservation(@RequestBody FlightRequestDto flightRequestDto){
        return null;
    }

}

