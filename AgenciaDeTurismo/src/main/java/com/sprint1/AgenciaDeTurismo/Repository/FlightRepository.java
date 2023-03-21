package com.sprint1.AgenciaDeTurismo.Repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.sprint1.AgenciaDeTurismo.DTO.FlightDto;
import com.sprint1.AgenciaDeTurismo.Exception.BadRequestException;
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

    public FlightRepository() throws IOException {
        this.flights=loadDataBase("classpath:dataFlights.json");
    }
    ModelMapper modelMapper = new ModelMapper();

    // US 0004
    public List<FlightDto> dataFlights() {
        return flights.stream()
                .map(flightModel -> modelMapper.map(flightModel, FlightDto.class))
                .collect(Collectors.toList());
    }

    // US 0005
    public List<FlightDto> getFlightAvailability(LocalDate dateFrom, LocalDate dateTo, String origin, String destination) {
        // Validar que el origen y destino sean los mismos que los de la lista de vuelos
        if (!isSameOriginAndDestination(origin, destination)) {
            throw new BadRequestException("El origen y/o destino no son válidos");
        }

        return dataFlights().stream().filter(flight -> flight.getDestiny().equalsIgnoreCase(destination) &&
                !flight.getDateFrom().isAfter(dateFrom) &&
                !flight.getDateTo().isBefore(dateTo) &&
                flight.getOrigin().equalsIgnoreCase(origin)).collect(Collectors.toList());
    }

    public boolean isSameOriginAndDestination(String origin, String destination) {
        return dataFlights().stream().anyMatch(flight -> flight.getDestiny().equalsIgnoreCase(destination) && flight.getOrigin().equalsIgnoreCase(origin));
    }

    // US 0006
    public FlightModel findFlight(String numberFlight){
        return flights.stream()
                .filter(flight -> flight.getNumberFlight().equalsIgnoreCase(numberFlight)).findFirst()
                .orElseThrow(()-> new NotFoundException("No se encontro el vuelo"));

    }

    public List<FlightModel> loadDataBase(String classpath) throws IOException {
        List<FlightModel> flights = null;

        File file;
        ObjectMapper objectMapper = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .registerModule(new JavaTimeModule());
        TypeReference<List<FlightModel>> typeRef = new TypeReference<>() {};

        try {
            file = ResourceUtils.getFile(classpath);
            flights = objectMapper.readValue(file, typeRef);
        } catch (IOException e) {
            throw e;
        }

        return flights;
    }
}
