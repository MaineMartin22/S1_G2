package com.sprint1.AgenciaDeTurismo.unit.repository;

import com.sprint1.AgenciaDeTurismo.Model.HotelModel;
import com.sprint1.AgenciaDeTurismo.Repository.HotelRepository;
import com.sprint1.AgenciaDeTurismo.utils.HotelFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class HotelRepositoryTest {

    HotelRepository hotelRepository = new HotelRepository();

    @Test
    public void dataHotelsTest(){
        // arrange
        List<HotelModel> expected = new ArrayList<>();
        HotelModel cataratas = HotelFactory.getCataratasHotel();
        HotelModel bristol = HotelFactory.getBristol();

        expected.add(cataratas);
        expected.add(bristol);

        // act

        var result = hotelRepository.dataHotels();

        // assert

        Assertions.assertEquals(expected, result);

    }

    @Test
    public void getHotelDisponibleTest(){
        // arange
        List<HotelModel> expected = new ArrayList<>();
        HotelModel cataratas = HotelFactory.getCataratasHotel();

        String dateFrom = "2022-02-10";
        String dateTo = "2022-03-20";
        String destination = "Puerto Iguazú";

        expected.add(cataratas);

        // act
        var result = hotelRepository.getHotelDisponible(dateFrom, dateTo, destination);

        //assert

        Assertions.assertEquals(expected, result);
    }

    @Test
    public void findHotelWhitCodeTest(){
        // arange
        HotelModel expected = HotelFactory.getBristol();

        String code = "HB-0001";


        // act
        var result = hotelRepository.findHotelWhitCode(code);

        //assert

        Assertions.assertEquals(expected, result);
    }
}
