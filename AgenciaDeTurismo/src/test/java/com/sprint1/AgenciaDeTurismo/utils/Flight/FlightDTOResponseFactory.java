package com.sprint1.AgenciaDeTurismo.utils.Flight;

import com.sprint1.AgenciaDeTurismo.DTO.RequestDto.Flight.FlightReservationDTO;
import com.sprint1.AgenciaDeTurismo.DTO.RequestDto.PaymentMethodDto;
import com.sprint1.AgenciaDeTurismo.DTO.RequestDto.PeopleDto;
import com.sprint1.AgenciaDeTurismo.DTO.ResponseDto.Flight.FlightDTOResponse;
import com.sprint1.AgenciaDeTurismo.DTO.ResponseDto.Flight.FlightResponse;
import com.sprint1.AgenciaDeTurismo.utils.Data.PaymentMethodDTOFactory;
import com.sprint1.AgenciaDeTurismo.utils.Data.PeopleDTOFactory;

import java.time.LocalDate;
import java.util.List;

public class FlightDTOResponseFactory {

    public static FlightDTOResponse flightDTOResponseDebitBAPI() {
        return FlightDTOResponse.builder()
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


    public static FlightDTOResponse flightDTOResponseCreditThreeBAPI() {
        return FlightDTOResponse.builder()
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

    public static FlightDTOResponse flightDTOResponseCreditSixPIBA(){
        return FlightDTOResponse.builder()
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
    public static FlightDTOResponse  flightDTOResponseCreditTwelvePIBA(){
        return FlightDTOResponse.builder()
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
