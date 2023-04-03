package com.sprint1.AgenciaDeTurismo.utils.Hotel;

import com.sprint1.AgenciaDeTurismo.DTO.HotelDTO;
import com.sprint1.AgenciaDeTurismo.Entity.Hotel;

import java.time.LocalDate;

public class HotelDTOFactory {
    public static HotelDTO getCataratasHotelDTO() {
        return HotelDTO.builder().hotelCode("CH-0002")
                .id(1)
                .name("Cataratas Hotel")
                .city("Puerto Iguazú")
                .typeRoom("Doble")
                .priceForNight(6300)
                .availabilityFrom(LocalDate.of(2022, 02, 10))
                .availabilityUntil(LocalDate.of(2022, 03, 20))
                .reserved(false)
                .build();

    }
    public static HotelDTO getBristolDTO() {
        return HotelDTO.builder().hotelCode("HB-0001")
                .id(2)
                .name("Hotel Bristol")
                .city("Buenos Aires")
                .typeRoom("Single")
                .priceForNight(5435)
                .availabilityFrom(LocalDate.of(2022, 02, 10))
                .availabilityUntil(LocalDate.of(2022, 03, 23))
                .reserved(false)
                .build();

    }
    public static HotelDTO getHotelEntity() {
        return HotelDTO.builder().hotelCode("HB-0001")
                .id(3)
                .name("Nuevo hotel")
                .city("Rafaela")
                .typeRoom("Triple")
                .priceForNight(5005)
                .availabilityFrom(LocalDate.of(2022, 02, 10))
                .availabilityUntil(LocalDate.of(2022, 03, 23))
                .reserved(false)
                .build();

    }
}
