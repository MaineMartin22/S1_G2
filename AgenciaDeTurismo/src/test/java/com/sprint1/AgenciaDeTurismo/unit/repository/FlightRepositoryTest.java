package com.sprint1.AgenciaDeTurismo.unit.repository;

import com.sprint1.AgenciaDeTurismo.DTO.HotelDTO;
import com.sprint1.AgenciaDeTurismo.DTO.RequestDto.Flight.FlightDto;
import com.sprint1.AgenciaDeTurismo.Model.FlightModel;
import com.sprint1.AgenciaDeTurismo.Repository.FlightRepository;
import com.sprint1.AgenciaDeTurismo.utils.FlightDTOFactory;
import com.sprint1.AgenciaDeTurismo.utils.FlightFactory;
import com.sprint1.AgenciaDeTurismo.utils.HotelDTOFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FlightRepositoryTest {
    FlightRepository flightRepository = new FlightRepository();

    @Test
    void dataFlights() {
        // arrange
        List<FlightDto> expected = List.of(FlightDTOFactory.getBsAsPuertoIguazuDTO(),
                FlightDTOFactory.getPuertoIguazuBogotaDTO());

        // act

        var result = flightRepository.dataFlights();

        // assert
        Assertions.assertEquals(expected, result);
    }

    @Test
    void getFlightAvailability() {
        // arrange
        List<FlightDto> expected = List.of(FlightDTOFactory.getBsAsPuertoIguazuDTO());
        LocalDate dateFrom = LocalDate.of(2022,02,10);
        LocalDate dateTo= LocalDate.of(2022,02,15);
        String origin= "Buenos Aires";
        String destination = "Puerto Iguazú";
        // act

        var result = flightRepository.getFlightAvailability(dateFrom, dateTo, origin, destination);

        // assert
        Assertions.assertEquals(expected, result);
    }

    @Test
    void findFlight() {
        // arrange
        FlightModel expected = FlightFactory.getPuertoIguazuBogota();
        String code = "PIBA-1420";
        // act
        var result = flightRepository.findFlight(code);
        // assert
        Assertions.assertEquals(expected, result);

    }
}