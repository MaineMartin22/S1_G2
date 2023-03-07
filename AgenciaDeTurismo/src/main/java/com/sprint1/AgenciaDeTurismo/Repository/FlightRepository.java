package com.sprint1.AgenciaDeTurismo.Repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.sprint1.AgenciaDeTurismo.Exception.NotFoundException;
import com.sprint1.AgenciaDeTurismo.Model.FlightModel;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class FlightRepository {
    private List<FlightModel> flights = new ArrayList<>();

    public FlightRepository() {
        this.flights=loadDataBase();
    }
    public List<FlightModel> dataFlights (){
        return flights;
    }
    /*
    FlightModel flightModel1 = new FlightModel("BAPI-1235","Buenos Aires","Puerto Iguazú", "Economy",6500, LocalDate.of(2022,02,10),LocalDate.of(2022,02,15));
    FlightModel flightModel2 = new FlightModel("PIBA-1420", "Puerto Iguazú", "Bogotá", "Business",43200,LocalDate.of (2022,02,10), LocalDate.of (2022,02,20));
    FlightModel flightModel3 = new FlightModel("PIBA-1420", "Puerto Iguazú", "Bogotá", "Economy", 25735,LocalDate.of (2022,02,10), LocalDate.of (2022,02,21));
    FlightModel flightModel4 = new FlightModel("BATU-5536", "Buenos Aires", "Tucumán", "Economy", 7320,LocalDate.of (2022,02,10), LocalDate.of (2022,02,17));
    FlightModel flightModel5 = new FlightModel("TUPI-3369", "Tucumán", "Puerto Iguazú", "Business", 12530, LocalDate.of(2022,02,12), LocalDate.of (2022,02,23));
    FlightModel flightModel6 = new FlightModel("TUPI-3369", "Tucumán", "Puerto Iguazú", "Economy", 5400,LocalDate.of (2022,01,02), LocalDate.of (2022,01,16));
    FlightModel flightModel7 = new FlightModel("BOCA-4213", "Bogotá", "Cartagena", "Economy", 8000,LocalDate.of (2022,01,23), LocalDate.of (2022,2,5));
    FlightModel flightModel8 = new FlightModel("CAME-0321", "Cartagena", "Medellín", "Economy", 7800,LocalDate.of(2022,01,23), LocalDate.of (2022,1,31));
    FlightModel flightModel9 = new FlightModel("BOBA-6567", "Bogotá", "Buenos Aires", "Business", 57000, LocalDate.of(2022,02,15), LocalDate.of (2022,02,28));
    FlightModel flightModel10 = new FlightModel("BOBA-6567", "Bogotá", "Buenos Aires", "Economy", 39860, LocalDate.of(2022,03,01), LocalDate.of (2022,03,14));
    FlightModel flightModel11 = new FlightModel("BOME-4442", "Bogotá", "Medellín", "Economy", 11000, LocalDate.of(2022,02,10), LocalDate.of (2022,02,24));
    FlightModel flightModel12 = new FlightModel("MEPI-9986", "Medellín", "Puerto Iguazú", "Business", 41640, LocalDate.of(2022,04,17), LocalDate.of (2022,05,02));
    public List<FlightModel> dataFlights(){
        flights.add(flightModel1);
        flights.add(flightModel2);
        flights.add(flightModel3);
        flights.add(flightModel4);
        flights.add(flightModel5);
        flights.add(flightModel6);
        flights.add(flightModel7);
        flights.add(flightModel8);
        flights.add(flightModel9);
        flights.add(flightModel10);
        flights.add(flightModel11);
        flights.add(flightModel12);
        return flights;
    }*/

    public List<FlightModel> getFlightAvailability(String dateFrom, String dateTo, String origin, String destination) {

        List<FlightModel> flightModelAvailabilities = new ArrayList<>();
        LocalDate fechaComoLocalDateFrom = LocalDate.parse(dateFrom);
        LocalDate fechaComoLocalDateTo = LocalDate.parse(dateTo);

        for (FlightModel flightModel : flights) {
            if(
                    flightModel.getOrigin().toUpperCase().contains((origin.toUpperCase())) &&
                           flightModel.getDeparturaDate().isEqual(fechaComoLocalDateFrom) &&
                            flightModel.getReturnDate().isEqual(fechaComoLocalDateTo) &&
                            flightModel.getDestiny().toUpperCase().contains((destination.toUpperCase())))
            {

                flightModelAvailabilities.add(flightModel);
            }
        }
        return flightModelAvailabilities;

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
        } catch (IOException e) {
            e.printStackTrace();
        }

        return flights;
    }
}
