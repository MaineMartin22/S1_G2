package com.sprint1.AgenciaDeTurismo.DTO.RequestDto;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PeopleDto {
    private Integer id;
    @NotBlank(message = "El dni no puede estar vacío")
    private String dni;
    @NotBlank(message = "El nombre no puede estar vacío")
    private String name;
    @NotBlank(message = "El apellido no puede estar vacío")
    private String lastName;
    @NotBlank(message = "La fecha de cumpleaños no puede estar vacía")
    private String birthDate;
    @Email(message="Por favor ingrese un e-mail válido")
    private String mail;
}
