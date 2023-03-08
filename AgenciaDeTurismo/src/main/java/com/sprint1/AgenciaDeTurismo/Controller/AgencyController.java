package com.sprint1.AgenciaDeTurismo.Controller;

import com.sprint1.AgenciaDeTurismo.DTO.RequestDto.Flight.FlightRequestDto;
import com.sprint1.AgenciaDeTurismo.DTO.RequestDto.Hotel.BookingRequestDto;
import com.sprint1.AgenciaDeTurismo.DTO.ResponseDto.Flight.FlightResponse;
import com.sprint1.AgenciaDeTurismo.DTO.ResponseDto.Hotel.BookingResponse;
import com.sprint1.AgenciaDeTurismo.Model.FlightModel;
import com.sprint1.AgenciaDeTurismo.Model.HotelModel;
import com.sprint1.AgenciaDeTurismo.Service.FlightService;
import com.sprint1.AgenciaDeTurismo.Service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AgencyController {

    @Autowired
    HotelService hotelService;
    @Autowired
    FlightService flightService;

    // US 0001
    /*@GetMapping("/api/v1/hotels")
    public List<HotelModel> get() {
        return hotelService.findAll();
    }*/

    // US 0002
    @GetMapping("/api/v1/hotels")
    // /api/v1/hotels?dateFrom=dd/mm/aaaa&dateTo=dd/mm/aaaa&destination=Puerto Iguazu
    public List<HotelModel> hotelesDisponibles(@RequestParam(required = false) String dateFrom,
                                               @RequestParam(required = false) String dateTo,
                                               @RequestParam(required = false)  String destination) {
        return hotelService.getHotelDisponibles(dateFrom, dateTo, destination);
    }

    // US 0003
    @PostMapping("/api/v1/booking")
    public BookingResponse reservaHotel(@RequestBody BookingRequestDto bookingRequestDto) {
        return hotelService.reservationHotel(bookingRequestDto);
    }

    // US 0004
    @GetMapping("/api/v1/flights")
    public List<FlightModel> getFlights() {return flightService.getFlight(); }

        // US 0005
        @GetMapping("/api/v1/flight")
        // /api/v1/flights?dateFrom=dd/mm/aaaa&dateTo=dd/mm/aaaa&origin=Buenos Aires&destination=Puerto Iguazú
        public List<FlightModel> flightAvailability (@RequestParam String dateFrom, @RequestParam String
        dateTo, @RequestParam String origin, @RequestParam String destination){
            return flightService.getFlightAvailability(dateFrom, dateTo, origin, destination);
        }

        // US 0006
        @PostMapping("/api/v1/flight-reservation")
        public FlightResponse flightReservation (@RequestBody FlightRequestDto flightRequestDto){
            return flightService.reservationFlight(flightRequestDto);
        }


}

