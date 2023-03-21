package com.sprint1.AgenciaDeTurismo.unit.repository;

import com.sprint1.AgenciaDeTurismo.DTO.FlightDto;
import com.sprint1.AgenciaDeTurismo.DTO.HotelDTO;
import com.sprint1.AgenciaDeTurismo.Exception.BadRequestException;
import com.sprint1.AgenciaDeTurismo.Model.HotelModel;
import com.sprint1.AgenciaDeTurismo.Repository.HotelRepository;
import com.sprint1.AgenciaDeTurismo.utils.Flight.FlightDTOFactory;
import com.sprint1.AgenciaDeTurismo.utils.Hotel.HotelDTOFactory;
import com.sprint1.AgenciaDeTurismo.utils.Hotel.HotelFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class HotelRepositoryTest {

    HotelRepository hotelRepository = new HotelRepository();

    @Test
    public void dataHotelsTest(){
        // arrange
        List<HotelDTO> expected = List.of(HotelDTOFactory.getCataratasHotelDTO(),
                HotelDTOFactory.getBristolDTO());

        // act

        var result = hotelRepository.dataHotels();

        // assert
        Assertions.assertEquals(expected, result);

    }

    @Test
    public void getHotelDisponibleTest(){
        // arrange
        List<HotelDTO> expected = new ArrayList<>();
        HotelDTO cataratas = HotelDTOFactory.getCataratasHotelDTO();

        LocalDate dateFrom = LocalDate.of(2022, 02, 10);
        LocalDate dateTo = LocalDate.of(2022, 03, 20);
        String destination = "Puerto Iguazú";

        expected.add(cataratas);

        // act
        var result = hotelRepository.getHotelDisponible(dateFrom, dateTo, destination);

        //assert

        Assertions.assertEquals(expected, result);
    }

    @Test
    public void findHotelWhitCodeTest(){
        // arrange
        HotelModel expected = HotelFactory.getBristol();

        String code = "HB-0001";


        // act
        var result = hotelRepository.findHotelWhitCode(code);

        //assert

        Assertions.assertEquals(expected, result);
    }
    @Test
    public void getHotelDisponibleExceptionDestination(){
        //arrage

        LocalDate dateFrom = LocalDate.of(2022, 02, 10);
        LocalDate dateTo = LocalDate.of(2022, 03, 20);
        String destination = "Bolivia";

        // assert
        Assertions.assertThrows(
                BadRequestException.class,
                () -> hotelRepository.getHotelDisponible(dateFrom, dateTo, destination)
           );
    }
}
