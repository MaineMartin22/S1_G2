
package com.sprint1.AgenciaDeTurismo.utils.Hotel;

import com.sprint1.AgenciaDeTurismo.DTO.ResponseDto.Hotel.BookingResponseDTO;

import com.sprint1.AgenciaDeTurismo.utils.Data.PeopleDTOFactory;
import com.sprint1.AgenciaDeTurismo.utils.Data.StatusCodeDTOFactory;



public class BookingResponseFactory {


public static BookingResponseDTO getReservationHotelIguazuDebit() {
    return BookingResponseDTO.builder()
            .userName(PeopleDTOFactory.getPeopleOne().getMail())
            .totalNeto(239400)
            .totalIntereses(0)
            .totalFinal(239400)
            .booking(BookingResponseDTOFactory.bookingResponseDtoIguazuDoble())
            .statusCode(StatusCodeDTOFactory.getStatusCode())
            .build();
    }
    public static BookingResponseDTO getReservationHotelBsAsThreeDues() {
        return BookingResponseDTO.builder()
                .userName(PeopleDTOFactory.getPeopleOne().getMail())
                .totalNeto(54350)
                .totalIntereses(2717.5)
                .totalFinal(57067.5)
                .booking(BookingResponseDTOFactory.bookingResponseDtoBuenosAiresSinlge())
                .statusCode(StatusCodeDTOFactory.getStatusCode())
                .build();
    }


    public static BookingResponseDTO getReservationHotelBsAsSixDues() {
        return BookingResponseDTO.builder()
                .userName(PeopleDTOFactory.getPeopleOne().getMail())
                .totalNeto(54350)
                .totalIntereses(5435)
                .totalFinal(59785)
                .booking(BookingResponseDTOFactory.bookingResponseDtoBuenosAiresSinlge())
                .statusCode(StatusCodeDTOFactory.getStatusCode())
                .build();
    }
    public static BookingResponseDTO getReservationHotelBsAsTwelveDues() {
        return BookingResponseDTO.builder()
                .userName(PeopleDTOFactory.getPeopleOne().getMail())
                .totalNeto(54350)
                .totalIntereses(8152.5)
                .totalFinal(62502.5)
                .booking(BookingResponseDTOFactory.bookingResponseDtoBuenosAiresSinlge())
                .statusCode(StatusCodeDTOFactory.getStatusCode())
                .build();
    }


}

