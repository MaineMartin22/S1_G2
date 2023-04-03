package com.sprint1.AgenciaDeTurismo.Service;

import com.sprint1.AgenciaDeTurismo.DTO.FlightDto;
import com.sprint1.AgenciaDeTurismo.DTO.RequestDto.Flight.FlightRequestDto;
import com.sprint1.AgenciaDeTurismo.DTO.ResponseDto.Flight.FlightResponseDTO;
import com.sprint1.AgenciaDeTurismo.Service.Generics.ICrudService;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface IFlightService extends ICrudService<FlightDto, Integer> {
    List<FlightResponseDTO> getAllEntitiesResponse();

    FlightResponseDTO reservationFlight(@RequestBody FlightRequestDto flightRequestDto);

    FlightResponseDTO updateReservaEntity(@RequestBody FlightResponseDTO flightResponseDTO, Integer id);


}
