package com.sprint1.AgenciaDeTurismo.utils.Flight;

import com.sprint1.AgenciaDeTurismo.DTO.RequestDto.Flight.FlightRequestDto;
import com.sprint1.AgenciaDeTurismo.utils.Data.PeopleDTOFactory;

public class FlightRequestDTOFactory {
    public static FlightRequestDto getReservationCreditRefusedPaymentBAPI(){
        return FlightRequestDto.builder()
                .userName(PeopleDTOFactory.getPeopleOne().getMail())
                .flightReservation(FlightReservationDTOFactory.getReservationCreditRefusedPaymentBAPI())
                .build();
    }
    public static FlightRequestDto getReservationDebitBAPI(){
        return FlightRequestDto.builder()
                .userName(PeopleDTOFactory.getPeopleOne().getMail())
                .flightReservation(FlightReservationDTOFactory.getReservationDebitBAPI())
                .build();
    }
    public static FlightRequestDto getReservationCreditThreeBAPI(){
        return FlightRequestDto.builder()
                .userName(PeopleDTOFactory.getPeopleThree().getMail())
                .flightReservation(FlightReservationDTOFactory.getReservationCreditThreeBAPI())
                .build();
    }
    public static FlightRequestDto getReservationCreditSixPIBA(){
        return FlightRequestDto.builder()
                .userName(PeopleDTOFactory.getPeopleFive().getMail())
                .flightReservation(FlightReservationDTOFactory.getReservationCreditSixPIBA())
                .build();
    }
    public static FlightRequestDto getReservationCreditTwelvePIBA(){
        return FlightRequestDto.builder()
                .userName(PeopleDTOFactory.getPeopleThree().getMail())
                .flightReservation(FlightReservationDTOFactory.getReservationCreditTwelvePIBA())
                .build();
    }
    public static FlightRequestDto getNoReservation() {
        return FlightRequestDto.builder()
                .userName(PeopleDTOFactory.getPeopleThree().getMail())
                .flightReservation(FlightReservationDTOFactory.getNoReservation())
                .build();
    }

}


