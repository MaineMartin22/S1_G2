package com.sprint1.AgenciaDeTurismo.Repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.sprint1.AgenciaDeTurismo.DTO.RequestDto.Flight.FlightDto;
import com.sprint1.AgenciaDeTurismo.Exception.NotFoundException;
import com.sprint1.AgenciaDeTurismo.Model.FlightModel;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class FlightRepository {
    private List<FlightModel> flights;

    public FlightRepository() {
        this.flights=loadDataBase();
    }
    ModelMapper modelMapper = new ModelMapper();

    public List<FlightDto> dataFlights() {
        return flights.stream()
                .map(flightModel -> modelMapper.map(flightModel, FlightDto.class))
                .collect(Collectors.toList());
    }

    public List<FlightDto> getFlightAvailability(LocalDate dateFrom, LocalDate dateTo, String origin, String destination) {

        return dataFlights().stream()
                .filter(flight -> flight.getOrigin().toUpperCase().contains(origin.toUpperCase()))
                .filter(flight -> flight.getDestiny().toUpperCase().contains(destination.toUpperCase()))
                .filter(flight -> flight.getDateFrom().isEqual(dateFrom))
                .filter(flight -> flight.getDateTo().isEqual(dateTo))
                .collect(Collectors.toList());
    }




    public FlightModel findFlight(String numberFlight){
        return flights.stream().filter(flight -> flight.getNumberFlight().equalsIgnoreCase(numberFlight)).findFirst().orElseThrow(()-> new NotFoundException("No se encontro el vuelo"));

    }
    private List<FlightModel> loadDataBase() {
        List<FlightModel> flights = null;

        File file;
        ObjectMapper objectMapper = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .registerModule(new JavaTimeModule());
        TypeReference<List<FlightModel>> typeRef = new TypeReference<>() {};

        try {
            file = ResourceUtils.getFile("classpath:dataFlights.json");
            flights = objectMapper.readValue(file, typeRef);
        } catch (IOException e) {e.printStackTrace();
        }

        return flights;
    }
}
