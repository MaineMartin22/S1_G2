package com.sprint1.AgenciaDeTurismo.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sprint1.AgenciaDeTurismo.DTO.RequestDto.PaymentMethodDto;
import com.sprint1.AgenciaDeTurismo.DTO.RequestDto.PeopleDto;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class FlightDto {

     private Integer id;
     private String numberFlight;
     private String origin;
     private String destiny;
     private String seatType;
     private double priceForPerson;
     @NotBlank(message = "Necesitas ingresar un vuelo")
     @JsonFormat(pattern = "yyyy-MM-dd")
     private LocalDate dateFrom;
     @JsonFormat(pattern = "yyyy-MM-dd")
     private LocalDate dateTo;

}
