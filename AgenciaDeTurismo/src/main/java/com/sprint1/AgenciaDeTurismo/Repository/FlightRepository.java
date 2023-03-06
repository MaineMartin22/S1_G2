package com.sprint1.AgenciaDeTurismo.Repository;

import com.sprint1.AgenciaDeTurismo.Model.FlightModel;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class FlightRepository {
    private List<FlightModel> flightModels = new ArrayList<>();

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
        flightModels.add(flightModel1);
        flightModels.add(flightModel2);
        flightModels.add(flightModel3);
        flightModels.add(flightModel4);
        flightModels.add(flightModel5);
        flightModels.add(flightModel6);
        flightModels.add(flightModel7);
        flightModels.add(flightModel8);
        flightModels.add(flightModel9);
        flightModels.add(flightModel10);
        flightModels.add(flightModel11);
        flightModels.add(flightModel12);
        return flightModels;
    }

    public List<FlightModel> getFlightAvailability(String dateFrom, String dateTo, String origin, String destination) {

        List<FlightModel> flightModelAvailabilities = new ArrayList<>();
        LocalDate fechaComoLocalDateFrom = LocalDate.parse(dateFrom);
        LocalDate fechaComoLocalDateTo = LocalDate.parse(dateTo);

        for (FlightModel flightModel : dataFlights()) {
            if(
                    flightModel.getOrigin().toUpperCase().contains((origin.toUpperCase())) &&
                            (fechaComoLocalDateFrom.isAfter(flightModel.getDeparturaDate()) ||
                            fechaComoLocalDateFrom.getDayOfMonth() == flightModel.getDeparturaDate().getDayOfMonth()) &&
                            (fechaComoLocalDateTo.isBefore(flightModel.getReturnDate()) ||
                                    fechaComoLocalDateTo.getDayOfMonth() ==  flightModel.getReturnDate().getDayOfMonth()) &&
                            flightModel.getDestiny().toUpperCase().contains((destination.toUpperCase())))
            {

                flightModelAvailabilities.add(flightModel);
            }
        }
        return flightModelAvailabilities;

    }

    public FlightModel findflight(String numberFlight){
        return dataFlights().stream().filter(flight -> flight.getNumberFlight().equalsIgnoreCase(numberFlight)).findFirst().orElse(null);

    }
}
