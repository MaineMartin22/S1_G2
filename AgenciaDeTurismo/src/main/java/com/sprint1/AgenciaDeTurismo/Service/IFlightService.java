package com.sprint1.AgenciaDeTurismo.Service;

import com.sprint1.AgenciaDeTurismo.DTO.FlightDto;
import com.sprint1.AgenciaDeTurismo.DTO.RequestDto.Flight.FlightRequestDto;
import com.sprint1.AgenciaDeTurismo.DTO.ResponseDto.Flight.FlightResponse;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

public interface IFlightService {
    List<FlightDto> getFlight();

    List<FlightDto> getFlightAvailability(@RequestParam LocalDate dateFrom, @RequestParam LocalDate dateTo, @RequestParam String origin, @RequestParam String destination);

    FlightResponse reservationFlight(@RequestBody FlightRequestDto flightRequestDto);
}
