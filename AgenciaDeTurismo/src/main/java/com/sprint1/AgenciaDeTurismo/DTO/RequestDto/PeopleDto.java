package com.sprint1.AgenciaDeTurismo.DTO.RequestDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PeopleDto {
    private String dni;
    private String name;
    private String lastName;
    private String birthDate;

    @Email(message="Por favor ingrese un e-mail válido")
    private String mail;
}
