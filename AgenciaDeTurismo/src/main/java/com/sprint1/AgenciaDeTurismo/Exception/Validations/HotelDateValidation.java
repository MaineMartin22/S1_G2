package com.sprint1.AgenciaDeTurismo.Exception.Validations;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = HotelDateConstraint.class)
@Target( {ElementType.TYPE, ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)

public @interface HotelDateValidation {
    String message() default "La fecha de entrada debe ser menor a la de salida y viceversa";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
