package com.sprint1.AgenciaDeTurismo.Exception.Validations;

import com.sprint1.AgenciaDeTurismo.DTO.RequestDto.Hotel.BookingDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class HotelDateConstraint implements ConstraintValidator <HotelDateValidation, BookingDto> {
    @Override
    public void initialize(HotelDateValidation constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(BookingDto bookingDto, ConstraintValidatorContext constraintValidatorContext) {
        return bookingDto.getDateFrom().isBefore(bookingDto.getDateTo());
    }
}
