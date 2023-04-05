package com.sprint1.AgenciaDeTurismo.Service;

import com.sprint1.AgenciaDeTurismo.DTO.FlightDto;
import com.sprint1.AgenciaDeTurismo.DTO.ResponseDto.Flight.FlightResponseDTO;
import com.sprint1.AgenciaDeTurismo.Service.Generics.ICrudService;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
//cambios para lo individual
public interface IFlightService extends ICrudService<FlightDto,Integer>{
/*
        FlightResponseDTO updateReservaEntity(FlightResponseDTO flightResponseDTO, Integer id);

        List< FlightResponseDTO> getAllEntitiesResponse();

        List<FlightDto> getAllEntitiesByFlightNumber(String vuelo);

        FlightResponseDTO reserveFlight(FlightResponseDTO FlightResponseDTO);

        FlightResponseDTO updateReservation(FlightResponseDTO FlightResponseDTO, Integer id);

        List<FlightResponseDTO> getAllEntitiesForPrice(Double minPrice, Double maxPrice);

*/
//parte individual
List<FlightResponseDTO> getAllEntitiesByFlightNumber();

        FlightResponseDTO updateReservaEntity(FlightResponseDTO flightResponseDTO, Integer id);

        List<FlightResponseDTO> getAllEntitiesResponse();

        List<FlightDto> getAllEntitiesByFlightNumber(String flightNumber);
        FlightResponseDTO flightReserva(@RequestBody FlightResponseDTO flightResponseDTO);

        FlightResponseDTO actualiza(@RequestBody FlightResponseDTO bookingResponseDTO, Integer id);


        List<FlightResponseDTO> getPrecioTotal(Double desde, Double hasta);
}




