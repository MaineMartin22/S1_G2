package com.sprint1.AgenciaDeTurismo.Repository;

import com.sprint1.AgenciaDeTurismo.Model.Flight;
import com.sprint1.AgenciaDeTurismo.Model.Hotel;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class FlightRepository {
    private List<Flight> flights = new ArrayList<>();

    Flight flight1 = new Flight("BAPI-1235","Buenos Aires","Puerto Iguazú", "Economy",6500, LocalDate.of(2022,02,10),LocalDate.of(2022,02,15));
    Flight flight2 = new Flight("PIBA-1420", "Puerto Iguazú", "Bogotá", "Business",43200,LocalDate.of (2022,02,10), LocalDate.of (2022,02,20));
    Flight flight3 = new Flight("PIBA-1420", "Puerto Iguazú", "Bogotá", "Economy", 25735,LocalDate.of (2022,02,10), LocalDate.of (2022,02,21));
    Flight flight4 = new Flight("BATU-5536", "Buenos Aires", "Tucumán", "Economy", 7320,LocalDate.of (2022,02,10), LocalDate.of (2022,02,17));
    Flight flight5 = new Flight("TUPI-3369", "Tucumán", "Puerto Iguazú", "Business", 12530, LocalDate.of(2022,02,12), LocalDate.of (2022,02,23));
    Flight flight6 = new Flight("TUPI-3369", "Tucumán", "Puerto Iguazú", "Economy", 5400,LocalDate.of (2022,01,02), LocalDate.of (2022,01,16));
    Flight flight7 = new Flight("BOCA-4213", "Bogotá", "Cartagena", "Economy", 8000,LocalDate.of (2022,01,23), LocalDate.of (2022,2,5));
    Flight flight8 = new Flight("CAME-0321", "Cartagena", "Medellín", "Economy", 7800,LocalDate.of(2022,01,23), LocalDate.of (2022,1,31));
    Flight flight9 = new Flight("BOBA-6567", "Bogotá", "Buenos Aires", "Business", 57000, LocalDate.of(2022,02,15), LocalDate.of (2022,02,28));
    Flight flight10 = new Flight("BOBA-6567", "Bogotá", "Buenos Aires", "Economy", 39860, LocalDate.of(2022,03,01), LocalDate.of (2022,03,14));
    Flight flight11 = new Flight("BOME-4442", "Bogotá", "Medellín", "Economy", 11000, LocalDate.of(2022,02,10), LocalDate.of (2022,02,24));
    Flight flight12 = new Flight("MEPI-9986", "Medellín", "Puerto Iguazú", "Business", 41640, LocalDate.of(2022,04,17), LocalDate.of (2022,05,02));
    public List<Flight> dataFlights(){
        flights.add(flight1);
        flights.add(flight2);
        flights.add(flight3);
        flights.add(flight4);
        flights.add(flight5);
        flights.add(flight6);
        flights.add(flight7);
        flights.add(flight8);
        flights.add(flight9);
        flights.add(flight10);
        flights.add(flight11);
        flights.add(flight12);
        return flights;
    }

    public List<Flight> getFlightAvailability(String dateFrom, String dateTo, String origin,  String destination) {

        List<Flight>  flightAvailabilitys = new ArrayList<>();
        LocalDate fechaComoLocalDateFrom = LocalDate.parse(dateFrom);
        LocalDate fechaComoLocalDateTo = LocalDate.parse(dateTo);

        for (Flight flight : dataFlights()) {
            if(
                    flight.getOrigin().toUpperCase().contains((origin.toUpperCase())) &&
                            (fechaComoLocalDateFrom.isAfter(flight.getDeparturaDate()) ||
                            fechaComoLocalDateFrom.getDayOfMonth() == flight.getDeparturaDate().getDayOfMonth()) &&
                            (fechaComoLocalDateTo.isBefore(flight.getReturnDate()) ||
                                    fechaComoLocalDateTo.getDayOfMonth() ==  flight.getReturnDate().getDayOfMonth()) &&
                            flight.getDestiny().toUpperCase().contains((destination.toUpperCase())))
            {

                flightAvailabilitys.add(flight);
            }
        }
        return flightAvailabilitys;

    }

}
