package com.sprint1.AgenciaDeTurismo.DTO.RequestDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PeopleDto {
    private String dni;
    private String name;
    private String lastName;
    private String birthDate;
    private String mail;
}
