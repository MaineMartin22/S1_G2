package com.sprint1.AgenciaDeTurismo.DTO.RequestDto.Hotel;

import com.sprint1.AgenciaDeTurismo.DTO.RequestDto.PaymentMethodDto;
import com.sprint1.AgenciaDeTurismo.DTO.RequestDto.PeopleDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BookingDto {
        private String dateFrom;
        private String dateTo;
        private String destination;
        private String hotelCode;
        private Integer peopleAmount;
        private String roomType;
        private PeopleDto people;
        private PaymentMethodDto paymentMethod;

}
