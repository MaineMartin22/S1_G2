package com.sprint1.AgenciaDeTurismo.unit.service;

import com.sprint1.AgenciaDeTurismo.DTO.HotelDTO;
import com.sprint1.AgenciaDeTurismo.DTO.RequestDto.Flight.FlightDto;
import com.sprint1.AgenciaDeTurismo.Repository.HotelRepository;
import com.sprint1.AgenciaDeTurismo.Service.HotelService;
import com.sprint1.AgenciaDeTurismo.utils.FlightDTOFactory;
import com.sprint1.AgenciaDeTurismo.utils.HotelDTOFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class HotelServiceTest {
    @Mock
    HotelRepository hotelRepository;

    @InjectMocks
    HotelService hotelService;

    @Test
    void findAll() {
        // arrange
        List<HotelDTO> expected = List.of(HotelDTOFactory.getCataratasHotelDTO(),
                HotelDTOFactory.getBristolDTO());
        // act
        Mockito.when(hotelRepository.dataHotels()).thenReturn(expected);
        var result = hotelService.findAll();

        // assert
        Assertions.assertEquals(expected, result);
    }

    @Test
    void getHotelDisponibles() {
        // arrange
        List<HotelDTO> expected = List.of(HotelDTOFactory.getCataratasHotelDTO());

        LocalDate dateFrom = LocalDate.of(2022,02,10);
        LocalDate dateTo= LocalDate.of(2022,02,20);
        String destination = "Puerto Iguazú";
        // act
        Mockito.when(hotelRepository.getHotelDisponible(dateFrom, dateTo, destination)).thenReturn(expected);
        var result = hotelService.getHotelDisponibles(dateFrom, dateTo, destination);

        // assert
        Assertions.assertEquals(expected, result);
    }

    @Test
    void reservationHotel() {

    }
}