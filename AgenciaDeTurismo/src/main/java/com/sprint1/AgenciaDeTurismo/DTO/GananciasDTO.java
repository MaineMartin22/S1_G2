package com.sprint1.AgenciaDeTurismo.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class GananciasDTO {

    public String message;

    public Double count;

    public String reservationCount;
    public String companyProfit;
}
