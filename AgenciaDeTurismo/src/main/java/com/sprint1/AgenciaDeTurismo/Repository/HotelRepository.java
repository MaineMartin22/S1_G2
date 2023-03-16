package com.sprint1.AgenciaDeTurismo.Repository;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.sprint1.AgenciaDeTurismo.DTO.HotelDTO;
import com.sprint1.AgenciaDeTurismo.Exception.NotFoundException;
import com.sprint1.AgenciaDeTurismo.Model.HotelModel;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Repository
public class HotelRepository {

    private List<HotelModel> hotels;

    public HotelRepository() {
        this.hotels = this.loadDataBase();
    }

    ModelMapper modelMapper = new ModelMapper();

    public List<HotelDTO> dataHotels() {
        return hotels.stream()
                .map(hotelModel -> modelMapper.map(hotelModel, HotelDTO.class))
                .collect(Collectors.toList());
    }



    public List<HotelDTO> getHotelDisponible(String dateFrom, String dateTo, String destination) {
        LocalDate fechaDesde = LocalDate.parse(dateFrom);
        LocalDate fechaHasta = LocalDate.parse(dateTo);

        return dataHotels().stream()
                .filter(hotel -> hotel.getCity().toUpperCase().contains(destination.toUpperCase()))
                .filter(hotel -> !hotel.isReserved())
                .filter(hotel -> (fechaDesde.isAfter(hotel.getAvailabilityFrom()) || fechaDesde.getDayOfMonth() == hotel.getAvailabilityFrom().getDayOfMonth()))
                .filter(hotel -> (fechaHasta.isBefore(hotel.getAvailabilityUntil()) || fechaHasta.getDayOfMonth() == hotel.getAvailabilityUntil().getDayOfMonth()))
                .collect(Collectors.toList());
    }


    public HotelModel findHotelWhitCode(String code) {
        return hotels.stream().filter(hotel -> hotel.getHotelCode().equalsIgnoreCase(code)).findFirst().orElseThrow(() -> new NotFoundException("No se encontró el hotel"));
    }

    private List<HotelModel> loadDataBase() {
        List<HotelModel> hotels = null;

        File file;
        ObjectMapper objectMapper = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .registerModule(new JavaTimeModule());
        TypeReference<List<HotelModel>> typeRef = new TypeReference<>() {
        };

        try {
            file = ResourceUtils.getFile("classpath:dataHotels.json");
            hotels = objectMapper.readValue(file, typeRef);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return hotels;
    }

}
