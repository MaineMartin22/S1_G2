package com.sprint1.AgenciaDeTurismo.DTO.RequestDto.Flight;

import com.sprint1.AgenciaDeTurismo.DTO.FlightDTOResponse;
import com.sprint1.AgenciaDeTurismo.DTO.RequestDto.PaymentMethodDto;
import com.sprint1.AgenciaDeTurismo.DTO.RequestDto.PeopleDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class FlightRequestDto {
    private String userName;
    private FlightReservationDTO flightReservation;

}
