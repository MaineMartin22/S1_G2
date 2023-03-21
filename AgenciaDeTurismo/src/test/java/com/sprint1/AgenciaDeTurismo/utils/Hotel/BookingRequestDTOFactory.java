package com.sprint1.AgenciaDeTurismo.utils.Hotel;

import com.sprint1.AgenciaDeTurismo.DTO.RequestDto.Hotel.BookingDto;
import com.sprint1.AgenciaDeTurismo.DTO.RequestDto.Hotel.BookingRequestDto;
import com.sprint1.AgenciaDeTurismo.utils.Data.PeopleDTOFactory;

import javax.validation.Valid;

public class BookingRequestDTOFactory {
    public static BookingRequestDto bookingDtoPuertoIguazuDobleDebit(){
        return BookingRequestDto.builder()
                .userName(PeopleDTOFactory.getPeopleOne().getMail())
                .booking(BookingDTOFactory.bookingDtoPuertoIguazuDobleDebit())
                .build();
    }
    public static BookingRequestDto bookingDtoBuenosAiresSingleThreeDues(){
        return BookingRequestDto.builder()
                .userName(PeopleDTOFactory.getPeopleOne().getMail())
                .booking(BookingDTOFactory.bookingDtoBuenosAiresSingleThreeDues())
                .build();
    }
    public static BookingRequestDto bookingDtoBuenosAiresSingleSixeDues(){
        return BookingRequestDto.builder()
                .userName(PeopleDTOFactory.getPeopleOne().getMail())
                .booking(BookingDTOFactory.bookingDtoBuenosAiresSingleSixeDues())
                .build();
    }
    public static BookingRequestDto bookingDtoBuenosAiresSingleTwelveDues(){
        return BookingRequestDto.builder()
                .userName(PeopleDTOFactory.getPeopleOne().getMail())
                .booking(BookingDTOFactory.bookingDtoBuenosAiresSingleTwelveDues())
                .build();
    }
    public static BookingRequestDto bookingDtoPuertoIguazuDoblegetRefused(){
        return BookingRequestDto.builder()
                .userName(PeopleDTOFactory.getPeopleOne().getMail())
                .booking(BookingDTOFactory.bookingDtoPuertoIguazuDoblegetRefused())
                .build();
    }
    public static BookingRequestDto getHotelesNoDisponibles(){
        return BookingRequestDto.builder()
                .userName(PeopleDTOFactory.getPeopleThree().getMail())
                .booking(BookingDTOFactory.getNoHotelesDisponibles())
                .build();
    }

}
