package com.sprint1.AgenciaDeTurismo.utils.Hotel;

import com.sprint1.AgenciaDeTurismo.DTO.RequestDto.PeopleDto;
import com.sprint1.AgenciaDeTurismo.DTO.ResponseDto.Hotel.BookingResponseDto;

import java.time.LocalDate;
import java.util.List;

public class BookingResponseDTOFactory {
    public static BookingResponseDto bookingResponseDto(){
        return BookingResponseDto.builder()
                .dateFrom()
                .dateTo()
                .destination()
                .hotelCode()
                .peopleAmount()
                .roomType()
                .people()
                .build();
    }
}

