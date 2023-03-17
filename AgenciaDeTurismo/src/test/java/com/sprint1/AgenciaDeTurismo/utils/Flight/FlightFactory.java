package com.sprint1.AgenciaDeTurismo.utils.Flight;

import com.sprint1.AgenciaDeTurismo.DTO.HotelDTO;
import com.sprint1.AgenciaDeTurismo.Model.FlightModel;

import java.time.LocalDate;

public class FlightFactory {
    public static FlightModel getBsAsPuertoIguazu(){
        return FlightModel.builder()
                .numberFlight("BAPI-1235")
                .origin("Buenos Aires")
                .destiny("Puerto Iguazú")
                .seatType("Economy")
                .priceForPerson(6500.0)
                .dateFrom(LocalDate.of(2022,02,10))
                .dateTo(LocalDate.of(2022,02,15))
                .build();
    }
    public static FlightModel getPuertoIguazuBogota(){
        return FlightModel.builder()
                .numberFlight("PIBA-1420")
                .origin("Puerto Iguazú")
                .destiny("Bogotá")
                .seatType("Business")
                .priceForPerson(43200.0)
                .dateFrom(LocalDate.of(2022,02,10))
                .dateTo(LocalDate.of(2022,02,20))
                .build();
    }
}