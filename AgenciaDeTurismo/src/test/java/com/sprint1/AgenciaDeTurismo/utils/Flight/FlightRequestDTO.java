package com.sprint1.AgenciaDeTurismo.utils.Flight;

import com.sprint1.AgenciaDeTurismo.DTO.RequestDto.Flight.FlightRequestDto;
import com.sprint1.AgenciaDeTurismo.utils.Data.PeopleDTOFactory;

public class FlightRequestDTO {
    public FlightRequestDto getReservationCreditRefusedPaymentBAPI(){
        return FlightRequestDto.builder()
                .userName(PeopleDTOFactory.getPeopleOne().getMail())
                .flightReservation(FlightReservationDTOFactory.getReservationCreditRefusedPaymentBAPI())
                .build();
    }
    public FlightRequestDto getReservationDebitBAPI(){
        return FlightRequestDto.builder()
                .userName(PeopleDTOFactory.getPeopleOne().getMail())
                .flightReservation(FlightReservationDTOFactory.getReservationDebitBAPI())
                .build();
    }
    public FlightRequestDto getReservationCreditThreeBAPI(){
        return FlightRequestDto.builder()
                .userName(PeopleDTOFactory.getPeopleThree().getMail())
                .flightReservation(FlightReservationDTOFactory.getReservationCreditThreeBAPI())
                .build();
    }
    public FlightRequestDto getReservationCreditSixPIBA(){
        return FlightRequestDto.builder()
                .userName(PeopleDTOFactory.getPeopleFive().getMail())
                .flightReservation(FlightReservationDTOFactory.getReservationCreditSixPIBA())
                .build();
    }
    public FlightRequestDto getReservationCreditTwelvePIBA(){
        return FlightRequestDto.builder()
                .userName(PeopleDTOFactory.getPeopleThree().getMail())
                .flightReservation(FlightReservationDTOFactory.getReservationCreditTwelvePIBA())
                .build();
    }

}


