package com.sprint1.AgenciaDeTurismo.utils.Hotel;

import com.sprint1.AgenciaDeTurismo.DTO.ResponseDto.Hotel.BookingResponseDetailsDTO;
import com.sprint1.AgenciaDeTurismo.utils.Data.PeopleDTOFactory;
import java.time.LocalDate;
import java.util.List;



public class BookingResponseDTOFactory {
    public static BookingResponseDetailsDTO bookingResponseDtoIguazuDoble(){
        return BookingResponseDetailsDTO.builder()
                .dateFrom(LocalDate.of(2022, 02, 10))
                .dateTo(LocalDate.of(2022, 03, 20))
                .destination("Puerto Iguazú")
                .hotelCode("CH-0002")
                .peopleAmount(2)
                .roomType("Doble")
                .people(
                        List.of(
                                PeopleDTOFactory.getPeopleOne(),
                                PeopleDTOFactory.getPeopleTwo()
                        )
                )
                .build();
    }
    public static BookingResponseDetailsDTO bookingResponseDtoBuenosAiresSinlge(){
        return BookingResponseDetailsDTO.builder()
                .dateFrom(LocalDate.of(2022, 02, 10))
                .dateTo(LocalDate.of(2022, 02, 20))
                .destination("Buenos Aires")
                .hotelCode("HB-0001")
                .peopleAmount(1)
                .roomType("Single")
                .people(
                        List.of(
                                PeopleDTOFactory.getPeopleThree()
                        )
                )
                .build();
    }
}

