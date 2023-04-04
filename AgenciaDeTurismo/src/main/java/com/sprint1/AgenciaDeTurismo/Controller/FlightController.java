package com.sprint1.AgenciaDeTurismo.Controller;


import com.sprint1.AgenciaDeTurismo.DTO.DestinoMasSolicitado;
import com.sprint1.AgenciaDeTurismo.DTO.ErrorDTO;
import com.sprint1.AgenciaDeTurismo.DTO.FlightDto;
import com.sprint1.AgenciaDeTurismo.DTO.GananciasDTO;
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

    //Lista de todos los vuelos y vuelos según filtros.
    @GetMapping("/api/v1/flights")
    // /api/v1/flights?dateFrom=dd/mm/aaaa&dateTo=dd/mm/aaaa&origin=Buenos Aires&destination=Puerto Iguazú
    public List<FlightDto> flightAvailability(@RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dateFrom,
                                              @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dateTo,
                                              @RequestParam(required = false) @Size(min = 3, message = "Debe ingresar un origen") String origin,
                                              @RequestParam(required = false) @Size(min = 3, message = "Debe ingresar un destino") String destiny) {
        return flightService.findFlightAvailable(dateFrom, dateTo, origin, destiny);
    }

    // Alta de un nuevo vuelo.
    @PostMapping("/api/v1/flights/new")
    public FlightDto saveEntity(@RequestBody FlightDto flightDto) {
        return flightService.saveEntity(flightDto);
    }

    // Reserva de un vuelo.
    @PostMapping("/api/v1/flight-reservation")
    public FlightResponseDTO flightReservation(@RequestBody @Valid FlightRequestDto flightRequestDto) {
        return flightService.reservationFlight(flightRequestDto);
    }

    // Busca vuelo por el código.
    @GetMapping("/api/v1/flights/findOneWhit")
    public FlightDto findFlightByCode(@RequestParam String code) {
        return flightService.getEntityByCode(code);
    }

    // Elimina un vuelo.
    @DeleteMapping("/api/v1/flights/delete")
    public ErrorDTO deleteByCode(@RequestParam String code){
        return flightService.deleteEntity(code);
    }

    // Elimina una reserva de vuelo.
    @DeleteMapping("/api/v1/flight-reservation/delete")
    public ErrorDTO deleteReservationByID(@RequestParam Integer id){
        return flightService.deleteReservaEntity(id);
    }

    //Devuelve la lista de las reservas de vuelos
    @GetMapping("/api/v1/flight-reservations/")
    public List<FlightResponseDTO> reservasEnLaDB() {
        return flightService.getAllEntitiesResponse();
    }

    // Actualiza un vuelo.
    @PutMapping("/api/v1/flights/edit")
    public FlightDto updateFlight(@RequestParam String code,@RequestBody FlightDto flightDto) {
        return flightService.updateEntity(flightDto, code);
    }

    // Actualiza una reserva de vuelo.
    @PutMapping("/api/v1/flight-reservation/edit")
    public FlightResponseDTO updateReservaFlight(@RequestParam Integer id,@RequestBody FlightResponseDTO flightResponseDTO) {
        return flightService.updateReservaEntity(flightResponseDTO, id);
    }

    //------------------------------------------------------------------------------------------------------------------------------//

    //Retorna el total de las ganancias de lo recaudado de los vuelos y las ganancias finales de la empresa.
    @GetMapping("/api/v1/flight-reservations/totalGanancias")
    public GananciasDTO gananciasTotales() {
        return flightService.totalEarnings();
    }
    // Retorna el destino mas elegido por los turistas.
    @GetMapping("/api/v1/flight-reservations/destinoMasSolicitado")
    public DestinoMasSolicitado getDestinoMasSolicitado() {
        return flightService.getDestinoMasSolicitado();
    }

}

