package com.sprint1.AgenciaDeTurismo.unit.service;

import com.sprint1.AgenciaDeTurismo.DTO.RequestDto.Flight.FlightDto;
import com.sprint1.AgenciaDeTurismo.Repository.FlightRepository;
import com.sprint1.AgenciaDeTurismo.Service.FlightService;
import com.sprint1.AgenciaDeTurismo.utils.Flight.FlightDTOFactory;
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
        List<FlightDto> expected = List.of(FlightDTOFactory.getPuertoIguazuBogotaDTO());
        LocalDate dateFrom = LocalDate.of(2022,03,10);
        LocalDate dateTo= LocalDate.of(2022,05,20);
        String origin= "Puerto ";
        String destination = "Bogotá";

        // act
        Mockito.when(flightRepository.getFlightAvailability(dateFrom, dateTo, origin, destination)).thenReturn(expected);
        var result = flightService.getFlightAvailability(dateFrom, dateTo, origin, destination);
        System.out.println(expected);
        System.out.println(result);
        // assert
        Assertions.assertEquals(expected.toString(), result.toString());

    }

    @Test
    void reservationFlight() {
    }
}