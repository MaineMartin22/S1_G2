package com.sprint1.AgenciaDeTurismo.Repository;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.sprint1.AgenciaDeTurismo.DTO.HotelDTO;
import com.sprint1.AgenciaDeTurismo.Exception.BadRequestException;
import com.sprint1.AgenciaDeTurismo.Exception.NotFoundException;
import com.sprint1.AgenciaDeTurismo.Model.HotelModel;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;


@Repository
public class HotelRepository {

    public List<HotelModel> hotels;

    public HotelRepository() throws IOException {
        this.hotels = loadDataBase("classpath:dataHotels.json");
    }

    ModelMapper modelMapper = new ModelMapper();

    public List<HotelDTO> dataHotels() {
        return hotels.stream()
                .map(hotelModel -> modelMapper.map(hotelModel, HotelDTO.class))
                .collect(Collectors.toList());
    }

    public List<HotelDTO> getHotelDisponible(LocalDate dateFrom, LocalDate dateTo, String destination) {
        if (!isSameDestination(destination)){
            throw new BadRequestException("No se encuentran hoteles en ese destino");
        }
        return dataHotels().stream().filter(hotel ->
                !hotel.getAvailabilityFrom().isAfter(dateFrom) &&
                !hotel.getAvailabilityUntil().isBefore(dateTo)&& hotel.getCity().equalsIgnoreCase(destination)).collect(Collectors.toList());
    }
    private boolean isSameDestination(String destination){
        return dataHotels().stream().anyMatch(hotel -> hotel.getCity().equalsIgnoreCase(destination));
    }

    public HotelModel findHotelWhitCode(String code) {
        return hotels.stream().filter(hotel -> hotel.getHotelCode().equalsIgnoreCase(code)).findFirst().orElseThrow(() -> new NotFoundException("No se encontró el hotel"));
    }
    public List<HotelModel> loadDataBase(String classpath) throws IOException {
        List<HotelModel> hotels = null;

        File file;
        ObjectMapper objectMapper = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .registerModule(new JavaTimeModule());
        TypeReference<List<HotelModel>> typeRef = new TypeReference<>() {
        };

        try {
            file = ResourceUtils.getFile(classpath);
            hotels = objectMapper.readValue(file, typeRef);
        } catch (IOException e) {
            throw e;
        }

        return hotels;
    }

}
