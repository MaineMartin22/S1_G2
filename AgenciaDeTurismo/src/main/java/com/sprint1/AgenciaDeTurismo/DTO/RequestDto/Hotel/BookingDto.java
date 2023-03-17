package com.sprint1.AgenciaDeTurismo.DTO.RequestDto.Hotel;

import com.sprint1.AgenciaDeTurismo.DTO.RequestDto.PaymentMethodDto;
import com.sprint1.AgenciaDeTurismo.DTO.RequestDto.PeopleDto;
import lombok.*;
import com.sprint1.AgenciaDeTurismo.Exception.Validations.HotelDateValidation;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@HotelDateValidation

public class  BookingDto {

        @DateTimeFormat(pattern = "yyyy-MM-dd")
        private LocalDate dateFrom;

        @DateTimeFormat(pattern = "yyyy-MM-dd")
        private LocalDate dateTo;

        @NotNull(message ="El destino no puede estar vacío")
        private String destination;
        private String hotelCode;

        //Consensuado con Scrum, requerimiento de Cantidad de personas no se realizará porque utilizamos un Integer//
        private Integer peopleAmount;
        //Consensuado con Scrum, requerimiento de Tipo de habitación relacionado con cantidad de personas, no se realizará porque es una anotacion personalizada//
        private String roomType;

        private List<PeopleDto> people;

        private PaymentMethodDto paymentMethod;

}
