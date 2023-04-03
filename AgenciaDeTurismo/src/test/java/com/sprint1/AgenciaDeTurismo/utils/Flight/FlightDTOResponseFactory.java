package com.sprint1.AgenciaDeTurismo.utils.Flight;

import com.sprint1.AgenciaDeTurismo.DTO.ResponseDto.Flight.FlightDTOResponseDetails;
import com.sprint1.AgenciaDeTurismo.utils.Data.PeopleDTOFactory;

import java.time.LocalDate;
import java.util.List;

public class FlightDTOResponseFactory {

    public static FlightDTOResponseDetails flightDTOResponseDebitBAPI() {
        return FlightDTOResponseDetails.builder()
                .dateFrom(LocalDate.of(2022,02,10))
                .dateTo(LocalDate.of(2022,02,15))
                .origin("Buenos Aires")
                .flightNumber("BAPI-1235")
                .destination("Puerto Iguazú")
                .seats(2)
                .seatType("Economy")
                .peopleDto(
                        List.of(
                                PeopleDTOFactory.getPeopleOne(),
                                PeopleDTOFactory.getPeopleTwo()
                        )
                )
                .build();
    }


    public static FlightDTOResponseDetails flightDTOResponseCreditThreeBAPI() {
        return FlightDTOResponseDetails.builder()
                .dateFrom(LocalDate.of(2022,02,10))
                .dateTo(LocalDate.of(2022,02,15))
                .origin("Buenos Aires")
                .flightNumber("BAPI-1235")
                .destination("Puerto Iguazú")
                .seats(2)
                .seatType("Economy")
                .peopleDto(
                        List.of(
                                PeopleDTOFactory.getPeopleThree(),
                                PeopleDTOFactory.getPeopleFour()
                        )
                )
                .build();
    }

    public static FlightDTOResponseDetails flightDTOResponseCreditSixPIBA(){
        return FlightDTOResponseDetails.builder()
                .dateFrom(LocalDate.of(2022,02,10))
                .dateTo(LocalDate.of(2022,02,20))
                .origin("Puerto Iguazú")
                .flightNumber("PIBA-1420")
                .destination("Bogotá")
                .seats(1)
                .seatType("Business")
                .peopleDto(
                        List.of(
                                PeopleDTOFactory.getPeopleFive()
                        )
                )
                .build();
    }
    public static FlightDTOResponseDetails flightDTOResponseCreditTwelvePIBA(){
        return FlightDTOResponseDetails.builder()
                .dateFrom(LocalDate.of(2022,02,10))
                .dateTo(LocalDate.of(2022,02,20))
                .origin("Puerto Iguazú")
                .flightNumber("PIBA-1420")
                .destination("Bogotá")
                .seats(2)
                .seatType("Business")
                .peopleDto(
                        List.of(
                                PeopleDTOFactory.getPeopleThree(),
                                PeopleDTOFactory.getPeopleFour()
                        )
                )
                .build();
    }

}
