package com.sprint1.AgenciaDeTurismo.DTO.RequestDto.Hotel;

import com.sprint1.AgenciaDeTurismo.DTO.RequestDto.PaymentMethodDto;
import com.sprint1.AgenciaDeTurismo.DTO.RequestDto.PeopleDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BookingDto {
        private LocalDate dateFrom;
        private LocalDate dateTo;
        private String destination;
        private String hotelCode;
        private Integer peopleAmount;
        private String roomType;
        private PeopleDto people;
        private PaymentMethodDto paymentMethod;

}
