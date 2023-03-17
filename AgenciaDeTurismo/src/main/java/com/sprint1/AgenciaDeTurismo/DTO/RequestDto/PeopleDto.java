package com.sprint1.AgenciaDeTurismo.DTO.RequestDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PeopleDto {
    @NotBlank
    private String dni;
    @NotBlank
    private String name;
    @NotBlank
    private String lastName;
    @NotBlank
    private String birthDate;

    @Email(message="Por favor ingrese un e-mail válido")
    private String mail;
}
