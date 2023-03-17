package com.sprint1.AgenciaDeTurismo.unit.service;

import com.sprint1.AgenciaDeTurismo.DTO.RequestDto.Flight.FlightDto;
import com.sprint1.AgenciaDeTurismo.DTO.RequestDto.Flight.FlightRequestDto;
import com.sprint1.AgenciaDeTurismo.DTO.ResponseDto.Flight.FlightResponse;
import com.sprint1.AgenciaDeTurismo.Exception.NotFoundException;
import com.sprint1.AgenciaDeTurismo.Model.FlightModel;
import com.sprint1.AgenciaDeTurismo.Repository.FlightRepository;
import com.sprint1.AgenciaDeTurismo.Service.FlightService;
import com.sprint1.AgenciaDeTurismo.utils.Flight.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class FlightServiceTest {

    @Mock
    FlightRepository flightRepository;

    @InjectMocks
    FlightService flightService;

    @Test
    void getFlight() {
        // arrange
        List<FlightDto> expected = List.of(FlightDTOFactory.getBsAsPuertoIguazuDTO(),
                FlightDTOFactory.getPuertoIguazuBogotaDTO());
        // act
        Mockito.when(flightRepository.dataFlights()).thenReturn(expected);
        var result = flightService.getFlight();

        // assert
        Assertions.assertEquals(expected, result);
    }

    @Test
    void getFlightAvailability() {
        // arrange
        List<FlightDto> vuelos = List.of(FlightDTOFactory.getBsAsPuertoIguazuDTO(),
                FlightDTOFactory.getPuertoIguazuBogotaDTO());


        List<FlightDto> expected = List.of(FlightDTOFactory.getBsAsPuertoIguazuDTO());
        LocalDate dateFrom = LocalDate.of(2022,02,10);
        LocalDate dateTo= LocalDate.of(2022,02,15);
        String origin= "Buenos Aires";
        String destination = "Puerto Iguazú";

        // act
        Mockito.when(flightRepository.getFlightAvailability(dateFrom, dateTo, origin, destination)).thenReturn(expected);
        Mockito.when(flightRepository.dataFlights()).thenReturn(vuelos);
        var result = flightService.getFlightAvailability(dateFrom, dateTo, origin, destination);
        // assert

        Assertions.assertEquals(expected, result);
    }

    @Test
    void getNotExistentFlightAvailability() {
        // arrange
        LocalDate dateFrom = LocalDate.of(2022,02,10);
        LocalDate dateTo= LocalDate.of(2022,02,20);
        String origin= "Buenos Aires";
        String destination = "Puerto Iguazú";

        // act
        Mockito.when(flightRepository.getFlightAvailability(dateFrom, dateTo, origin, destination)).thenReturn(List.of());

        // assert
        Assertions.assertThrows(
                NotFoundException.class,
                () -> flightService.getFlightAvailability(dateFrom, dateTo, origin, destination)
        );
    }


    @Test
    void reservationFlight() {
        // Arrange
        List<FlightDto> listaVuelos = List.of(FlightDTOFactory.getBsAsPuertoIguazuDTO(),
                FlightDTOFactory.getPuertoIguazuBogotaDTO());


        List<FlightDto> buscarVuelo = List.of(FlightDTOFactory.getBsAsPuertoIguazuDTO());
        LocalDate dateFrom = LocalDate.of(2022,02,10);
        LocalDate dateTo= LocalDate.of(2022,02,15);
        String origin= "Buenos Aires";
        String destination = "Puerto Iguazú";


        String code = FlightFactory.getBsAsPuertoIguazu().getNumberFlight();
        FlightModel returnCodigoVuelo = FlightFactory.getBsAsPuertoIguazu();


        FlightResponse expected = FlightResponseFactory.flightDTOResponseDebitBAPI(); // FlightDTOResponseFactory

        FlightRequestDto param = FlightRequestDTOFactory.getReservationDebitBAPI(); // FlightReservationDTOFactory

        // Act
        Mockito.when(flightRepository.dataFlights()).thenReturn(listaVuelos);
        Mockito.when(flightRepository.findFlight(code)).thenReturn(returnCodigoVuelo);
        Mockito.when(flightRepository.getFlightAvailability(dateFrom, dateTo, origin, destination)).thenReturn(buscarVuelo);
        var result = flightService.reservationFlight(param);

        // Assert
        Assertions.assertEquals(expected, result);


    }
}