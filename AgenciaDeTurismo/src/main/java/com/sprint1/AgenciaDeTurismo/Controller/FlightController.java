package com.sprint1.AgenciaDeTurismo.Controller;


import com.sprint1.AgenciaDeTurismo.DTO.ErrorDTO;
import com.sprint1.AgenciaDeTurismo.DTO.FlightDto;
import com.sprint1.AgenciaDeTurismo.DTO.RequestDto.Flight.FlightRequestDto;
import com.sprint1.AgenciaDeTurismo.DTO.ResponseDto.Flight.FlightResponseDTO;
import com.sprint1.AgenciaDeTurismo.Service.FlightService;
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
public class FlightController {
    @Autowired
    FlightService flightService;

    // US 0004 & 0005
    @GetMapping("/api/v1/flights")
    // /api/v1/flights?dateFrom=dd/mm/aaaa&dateTo=dd/mm/aaaa&origin=Buenos Aires&destination=Puerto Iguazú
    public List<FlightDto> flightAvailability(@RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dateFrom,
                                              @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dateTo,
                                              @RequestParam(required = false) @Size(min = 3, message = "Debe ingresar un origen") String origin,
                                              @RequestParam(required = false) @Size(min = 3, message = "Debe ingresar un destino") String destiny) {
        return flightService.findFlightAvailable(dateFrom, dateTo, origin, destiny);
    }

    // US 0006

    @PostMapping("/api/v1/flights/new")
    public FlightDto saveEntity(@RequestBody FlightDto flightDto) {
        return flightService.saveEntity(flightDto);
    }


    @PostMapping("/api/v1/flight-reservation")
    public FlightResponseDTO flightReservation(@RequestBody @Valid FlightRequestDto flightRequestDto) {
        return flightService.reservationFlight(flightRequestDto);
    }

    @GetMapping("/api/v1/flights/findOneWhit")
    public FlightDto findFlightByCode(@RequestParam String code) {
        return flightService.getEntityByCode(code);
    }

    @DeleteMapping("/api/v1/flights/delete")
    // /api/v1/flights/delete?flightNumber=number
    public ErrorDTO deleteByCode(@RequestParam String code){
        return flightService.deleteEntity(code);
    }


}

