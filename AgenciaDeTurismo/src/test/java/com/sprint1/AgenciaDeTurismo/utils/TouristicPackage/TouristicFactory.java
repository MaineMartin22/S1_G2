package com.sprint1.AgenciaDeTurismo.utils.TouristicPackage;

import com.sprint1.AgenciaDeTurismo.Entity.TouristicPackage;
import com.sprint1.AgenciaDeTurismo.utils.Data.PeopleDTOFactory;
import com.sprint1.AgenciaDeTurismo.utils.Flight.FlightDTOFactory;
import com.sprint1.AgenciaDeTurismo.utils.Flight.FlightFactory;
import com.sprint1.AgenciaDeTurismo.utils.Hotel.HotelFactory;

import java.time.LocalDate;
import java.util.List;

public class TouristicFactory {

    public static TouristicPackage getPaquete1() {
        return TouristicPackage.builder()
                .id(1)
                .name("Paquete1")
                .description("Disfruta de las cataratas en un hotel de lujo")
                .price(13000)
                .dateFrom(LocalDate.of(2022, 02, 10))
                .dateTo(LocalDate.of(2022, 03, 20))
                .flights(
                        List.of(
                                FlightFactory.getBsAsPuertoIguazu()
                        )
                )
                .hotels(List.of(
                        HotelFactory.getCataratasHotel()
                ))
                .build();

    }
    public static TouristicPackage getPaquete2() {
        return TouristicPackage.builder()
                .id(2)
                .name("Paquete2")
                .description("Disfruta de Brasil en un hotel de lujo")
                .price(12000)
                .dateFrom(LocalDate.of(2022, 02, 10))
                .dateTo(LocalDate.of(2022, 03, 20))
                .flights(
                        List.of(
                                FlightFactory.getPuertoIguazuBogota()
                        )
                )
                .hotels(List.of(
                        HotelFactory.getBristol()
                ))

                .build();

    }
}
