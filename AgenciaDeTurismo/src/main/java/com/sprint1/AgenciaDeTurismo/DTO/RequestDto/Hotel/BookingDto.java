package com.sprint1.AgenciaDeTurismo.DTO.RequestDto.Hotel;

import com.sprint1.AgenciaDeTurismo.DTO.RequestDto.PaymentMethodDto;
import com.sprint1.AgenciaDeTurismo.DTO.RequestDto.PeopleDto;
import lombok.*;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class    BookingDto {

        private LocalDate dateFrom;
        private LocalDate dateTo;

        @NotNull(message ="El destino no puede estar vacío")
        private String destination;
        private String hotelCode;
        private Integer peopleAmount;
        @AssertTrue(message="El tipo de habitación seleccionada no coincide con la cantidad de personas que se alojarán en ella")
        private String roomType;
        private List<PeopleDto> people;
        private PaymentMethodDto paymentMethod;

}
