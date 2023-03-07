package com.sprint1.AgenciaDeTurismo.Service;

import com.sprint1.AgenciaDeTurismo.DTO.RequestDto.Flight.FlightRequestDto;
import com.sprint1.AgenciaDeTurismo.DTO.ResponseDto.Flight.FlightResponse;
import com.sprint1.AgenciaDeTurismo.DTO.ResponseDto.Flight.FlightResponseDto;
import com.sprint1.AgenciaDeTurismo.DTO.StatusCodeDto;
import com.sprint1.AgenciaDeTurismo.Exception.NotFoundException;
import com.sprint1.AgenciaDeTurismo.Model.FlightModel;
import com.sprint1.AgenciaDeTurismo.Repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
public class FlightService {
    @Autowired
    FlightRepository flightRepository;
    // US 0004
    public List<FlightModel> getFlight() {
        return flightRepository.dataFlights();
    }

    // US 0005
    public List<FlightModel> getFlightAvailability(@RequestParam String dateFrom, @RequestParam String dateTo, @RequestParam String origin, @RequestParam String destination) {
        return flightRepository.getFlightAvailability(dateFrom, dateTo, origin, destination);
    }

    // US 0006
    public FlightResponse flightResponse(@RequestBody FlightRequestDto flightRequestDto) {
        FlightResponse response = new FlightResponse();
        FlightResponseDto flightResponseDto = new FlightResponseDto();

        if(flightRepository.dataFlights().isEmpty()){
            throw new NotFoundException("No se econtraron vuelos disponibles");
        }

        FlightModel reservationFlight = flightRepository.findFlight(flightRequestDto.getFlightReservation().getFlightNumber(), flightRequestDto.getFlightReservation().getSeatType());

        if(reservationFlight == null){
            throw new NotFoundException("No se encontraron vuelos");
        }

        flightResponseDto.setFlightNumber(flightRequestDto.getFlightReservation().getFlightNumber());
        flightResponseDto.setOrigin(flightRequestDto.getFlightReservation().getOrigin());
        flightResponseDto.setDestination(flightRequestDto.getFlightReservation().getDestination());
        flightResponseDto.setSeatType(flightRequestDto.getFlightReservation().getSeatType());
        flightResponseDto.setDateFrom(flightRequestDto.getFlightReservation().getDateFrom());
        flightResponseDto.setDateTo(flightRequestDto.getFlightReservation().getDateTo());
        flightResponseDto.setSeats(flightRequestDto.getFlightReservation().getSeats());
        flightResponseDto.setPeople(flightRequestDto.getFlightReservation().getPeople());


        Integer totalPrice = (int) (reservationFlight.getPriceForPerson() * flightRequestDto.getFlightReservation().getSeats());

        response.setUserName(flightRequestDto.getUserName());
        response.setTotal(totalPrice);
        response.setFlightReservation(flightResponseDto);
        response.setStatusCode(new StatusCodeDto(200, "Proceso termino satisfactoriamente"));

        return response;
    }
}
