package com.sprint1.AgenciaDeTurismo.Exception.Validations;

import com.sprint1.AgenciaDeTurismo.DTO.RequestDto.Flight.FlightRequestDto;
import com.sprint1.AgenciaDeTurismo.DTO.RequestDto.Flight.FlightReservationDTO;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class FlightDateConstraint implements ConstraintValidator<FlightDateValidation, FlightReservationDTO> {
        @Override
        public void initialize(FlightDateValidation constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

        @Override
        public boolean isValid(FlightReservationDTO flightReservationDTO, ConstraintValidatorContext
        constraintValidatorContext) {
            return flightReservationDTO.getDateFrom().isBefore(flightReservationDTO.getDateTo());

    }

}
