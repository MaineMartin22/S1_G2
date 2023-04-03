package com.sprint1.AgenciaDeTurismo.utils.Flight;

import com.sprint1.AgenciaDeTurismo.DTO.FlightDto;

import java.time.LocalDate;

public class FlightDTOFactory {
    public static FlightDto getBsAsPuertoIguazuDTO(){
        return FlightDto.builder()
                .id(1)
                .numberFlight("BAPI-1235")
                .origin("Buenos Aires")
                .destiny("Puerto Iguazú")
                .seatType("Economy")
                .priceForPerson(6500.0)
                .dateFrom(LocalDate.of(2022,02,10))
                .dateTo(LocalDate.of(2022,02,15))
                .build();
    }
    public static FlightDto getPuertoIguazuBogotaDTO(){
        return FlightDto.builder()
                .id(2)
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
