package com.sprint1.AgenciaDeTurismo.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorDTO {

    String explanation;

    List<String> messages;

}
