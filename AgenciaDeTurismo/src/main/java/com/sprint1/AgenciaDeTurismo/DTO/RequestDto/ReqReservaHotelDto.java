package com.sprint1.AgenciaDeTurismo.DTO.RequestDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ReqReservaHotelDto {
    private BookingDto bookingDto;
    private PeopleDto peopleDto;
    private PaymentMethodDto paymentMethodDto;

    public ReqReservaHotelDto(BookingDto bookingDto) {
        this.bookingDto = bookingDto;
    }
}
