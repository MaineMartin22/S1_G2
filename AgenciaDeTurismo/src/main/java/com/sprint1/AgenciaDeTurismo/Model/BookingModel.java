package com.sprint1.AgenciaDeTurismo.Model;

import com.sprint1.AgenciaDeTurismo.DTO.RequestDto.PaymentMethodDto;
import com.sprint1.AgenciaDeTurismo.DTO.RequestDto.PeopleDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BookingModel {
    private String dateFrom;
    private String dateTo;
    private String destination;
    private String hotelCode;
    private Integer peopleAmount;
    private String roomType;
    private List<PeopleDto> people;
    private PaymentMethodDto paymentMethod;
}
