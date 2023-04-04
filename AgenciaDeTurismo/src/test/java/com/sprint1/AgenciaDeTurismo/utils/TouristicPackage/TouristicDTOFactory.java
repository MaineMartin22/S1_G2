package com.sprint1.AgenciaDeTurismo.utils.TouristicPackage;

import com.sprint1.AgenciaDeTurismo.DTO.TouristicPackageDTO;
import com.sprint1.AgenciaDeTurismo.Entity.TouristicPackage;
import com.sprint1.AgenciaDeTurismo.utils.Flight.FlightDTOFactory;
import com.sprint1.AgenciaDeTurismo.utils.Flight.FlightFactory;
import com.sprint1.AgenciaDeTurismo.utils.Hotel.HotelDTOFactory;
import com.sprint1.AgenciaDeTurismo.utils.Hotel.HotelFactory;

import java.time.LocalDate;
import java.util.List;

public class TouristicDTOFactory {
    public static TouristicPackageDTO getPaquete1DTO() {
        return TouristicPackageDTO.builder()
                .id(1)
                .name("Paquete1")
                .description("Disfruta de las cataratas en un hotel de lujo")
                .price(13000)
                .dateFrom(LocalDate.of(2022, 02, 10))
                .dateTo(LocalDate.of(2022, 03, 20))
               .flights(
                        List.of()
                )
                .hotels(List.of())
                .build();


    }
    public static TouristicPackageDTO getPaquete2DTO() {
        return TouristicPackageDTO.builder()
                .id(2)
                .name("Paquete2")
                .description("Disfruta de Brasil en un hotel de lujo")
                .price(12000)
                .dateFrom(LocalDate.of(2022, 02, 10))
                .dateTo(LocalDate.of(2022, 03, 20))
               .flights(
                        List.of( )
                )
                .hotels(List.of( ))

                .build();


    }
}
