package com.sprint1.AgenciaDeTurismo.DTO.RequestDto.Flight;

import com.sprint1.AgenciaDeTurismo.DTO.RequestDto.PaymentMethodDto;
import com.sprint1.AgenciaDeTurismo.DTO.RequestDto.PeopleDto;
import com.sprint1.AgenciaDeTurismo.Exception.Validations.FlightDateValidation;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@FlightDateValidation
public class FlightReservationDTO {
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateFrom;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateTo;
    @NotNull(message ="El origen no puede estar vacío")
    private String origin;
    @NotNull(message = "El numero de vuelo no puede estar vacío")
    private String flightNumber;
    @NotNull(message ="El destino no puede estar vacío")
    private String destiny;
    private Integer seats;
    private String seatType;
    private List<PeopleDto> people;
    private PaymentMethodDto paymentMethod;
}