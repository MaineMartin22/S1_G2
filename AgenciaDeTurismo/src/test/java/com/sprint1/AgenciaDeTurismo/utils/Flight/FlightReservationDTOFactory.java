package com.sprint1.AgenciaDeTurismo.utils.Flight;

import com.sprint1.AgenciaDeTurismo.DTO.RequestDto.Flight.FlightReservationDTO;
import com.sprint1.AgenciaDeTurismo.utils.Data.PaymentMethodDTOFactory;
import com.sprint1.AgenciaDeTurismo.utils.Data.PeopleDTOFactory;
import java.time.LocalDate;
import java.util.List;

public class FlightReservationDTOFactory {
    public static FlightReservationDTO getReservationDebitBAPI(){
        return FlightReservationDTO.builder()
                .dateFrom(LocalDate.of(2022,02,10))
                .dateTo(LocalDate.of(2022,02,15))
                .origin("Buenos Aires")
                .flightNumber("BAPI-1235")
                .destiny("Puerto Iguazú")
                .seats(2)
                .seatType("Economy")
                .people(
                       List.of(
                               PeopleDTOFactory.getPeopleOne(),
                               PeopleDTOFactory.getPeopleTwo()
                       )
                )
                .paymentMethod(PaymentMethodDTOFactory.getPaymentDebit())
                .build();
    }
    public static FlightReservationDTO getReservationCreditRefusedPaymentBAPI(){
        return FlightReservationDTO.builder()
                .dateFrom(LocalDate.of(2022,02,10))
                .dateTo(LocalDate.of(2022,02,15))
                .origin("Buenos Aires")
                .flightNumber("BAPI-1235")
                .destiny("Puerto Iguazú")
                .seats(2)
                .seatType("Economy")
                .people(
                        List.of(
                                PeopleDTOFactory.getPeopleOne(),
                                PeopleDTOFactory.getPeopleTwo()
                        )
                )
                .paymentMethod(PaymentMethodDTOFactory.getPaymentCreditRefused())
                .build();
    }
    public static FlightReservationDTO getReservationCreditThreeBAPI(){
        return FlightReservationDTO.builder()
                .dateFrom(LocalDate.of(2022,02,10))
                .dateTo(LocalDate.of(2022,02,15))
                .origin("Buenos Aires")
                .flightNumber("BAPI-1235")
                .destiny("Puerto Iguazú")
                .seats(2)
                .seatType("Economy")
                .people(
                        List.of(
                                PeopleDTOFactory.getPeopleThree(),
                                PeopleDTOFactory.getPeopleFour()
                        )
                )
                .paymentMethod(PaymentMethodDTOFactory.getPaymentCreditThreeDues())
                .build();
    }

    public static FlightReservationDTO getReservationCreditSixPIBA(){
        return FlightReservationDTO.builder()
                .dateFrom(LocalDate.of(2022,02,10))
                .dateTo(LocalDate.of(2022,02,20))
                .origin("Puerto Iguazú")
                .flightNumber("PIBA-1420")
                .destiny("Bogotá")
                .seats(1)
                .seatType("Business")
                .people(
                        List.of(
                                PeopleDTOFactory.getPeopleFive()
                        )
                )
                .paymentMethod(PaymentMethodDTOFactory.getPaymentCreditSixDues())
                .build();
    }
    public static FlightReservationDTO getReservationCreditTwelvePIBA(){
        return FlightReservationDTO.builder()
                .dateFrom(LocalDate.of(2022,02,10))
                .dateTo(LocalDate.of(2022,02,20))
                .origin("Puerto Iguazú")
                .flightNumber("PIBA-1420")
                .destiny("Bogotá")
                .seats(2)
                .seatType("Business")
                .people(
                        List.of(
                                PeopleDTOFactory.getPeopleThree(),
                                PeopleDTOFactory.getPeopleFour()
                        )
                )
                .paymentMethod(PaymentMethodDTOFactory.getPaymentCreditTwelveDues())
                .build();
    }
    public static FlightReservationDTO getNoReservation(){
        return FlightReservationDTO.builder()
                .dateFrom(LocalDate.of(2022,03,10))
                .dateTo(LocalDate.of(2022,02,15))
                .origin("Tucuman")
                .flightNumber("BAPI-1238")
                .destiny("Purmamarca")
                .seats(2)
                .seatType("Economy")
                .people(
                        List.of(
                                PeopleDTOFactory.getPeopleThree(),
                                PeopleDTOFactory.getPeopleFour()
                        )
                )
                .paymentMethod(PaymentMethodDTOFactory.getPaymentCreditThreeDues())
                .build();
    }

}

