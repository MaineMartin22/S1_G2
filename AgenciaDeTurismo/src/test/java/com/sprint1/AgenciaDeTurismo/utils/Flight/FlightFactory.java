package com.sprint1.AgenciaDeTurismo.utils.Flight;

import com.sprint1.AgenciaDeTurismo.Entity.Flight;

import java.time.LocalDate;

public class FlightFactory {
    public static Flight getBsAsPuertoIguazu(){
        return Flight.builder()
                .id(1)
                .numberFlight("BAPI-1235")
                .origin("Buenos Aires")
                .destiny("Puerto Iguazú")
                .seatType("Economy")
                .priceForPerson(6500.0)
                .dateFrom(LocalDate.of(2022,02,10))
                .dateTo(LocalDate.of(2022,02,15))
                .totalSeats(4)
                .build();
    }
    public static Flight getPuertoIguazuBogota(){
        return Flight.builder()
                .id(2)
                .numberFlight("PIBA-1420")
                .origin("Puerto Iguazú")
                .destiny("Bogotá")
                .seatType("Business")
                .priceForPerson(43200.0)
                .dateFrom(LocalDate.of(2022,02,10))
                .dateTo(LocalDate.of(2022,02,20))
                .totalSeats(4)
                .build();
    }
}