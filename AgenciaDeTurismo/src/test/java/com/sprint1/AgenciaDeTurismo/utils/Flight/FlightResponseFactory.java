package com.sprint1.AgenciaDeTurismo.utils.Flight;

import com.sprint1.AgenciaDeTurismo.DTO.ResponseDto.Flight.FlightResponseDTO;
import com.sprint1.AgenciaDeTurismo.utils.Data.PeopleDTOFactory;
import com.sprint1.AgenciaDeTurismo.utils.Data.StatusCodeDTOFactory;

public class FlightResponseFactory {

    public static FlightResponseDTO flightDTOResponseDebitBAPI(){
        return FlightResponseDTO.builder()
                .userName(PeopleDTOFactory.getPeopleOne().getMail())
                .totalNeto(13000)
                .totalIntereses(0)
                .totalFinal(13000)
                .flightReservation(FlightDTOResponseFactory.flightDTOResponseDebitBAPI())
                .statusCode(StatusCodeDTOFactory.getStatusCode())
                .build();
    }
    public static FlightResponseDTO flightDTOResponseCreditThreeBAPI(){
        return FlightResponseDTO.builder()
                .userName(PeopleDTOFactory.getPeopleThree().getMail())
                .totalNeto(13000)
                .totalIntereses(650)
                .totalFinal(13650)
                .flightReservation(FlightDTOResponseFactory.flightDTOResponseCreditThreeBAPI())
                .statusCode(StatusCodeDTOFactory.getStatusCode())
                .build();
    }

    public static FlightResponseDTO flightDTOResponseCreditSixPIBA(){
        return FlightResponseDTO.builder()
                .userName(PeopleDTOFactory.getPeopleThree().getMail())
                .totalNeto(43200)
                .totalIntereses(4320)
                .totalFinal(47520)
                .flightReservation(FlightDTOResponseFactory.flightDTOResponseCreditSixPIBA())
                .statusCode(StatusCodeDTOFactory.getStatusCode())
                .build();
    }
    public static FlightResponseDTO flightDTOResponseCreditTwelvePIBA(){
        return FlightResponseDTO.builder()
                .userName(PeopleDTOFactory.getPeopleThree().getMail())
                .totalNeto(86400)
                .totalIntereses(12960)
                .totalFinal(99360)
                .flightReservation(FlightDTOResponseFactory.flightDTOResponseCreditTwelvePIBA())
                .statusCode(StatusCodeDTOFactory.getStatusCode())
                .build();
    }
}
