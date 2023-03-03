package com.sprint1.AgenciaDeTurismo.Repository;

import com.sprint1.AgenciaDeTurismo.Model.Hotel;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class HotelRepository {

    private List<Hotel> hotels = new ArrayList<>();

    Hotel hotel1 = new Hotel("CH-0002",
            "Cataratas Hotel",
            "Puerto Iguazú",
            "Doble",
            "$6300",
            LocalDate.of(2022, 02, 10),
            LocalDate.of(2022, 03, 20),
            false);
    Hotel hotel2 = new Hotel("CH-0003",
            "Cataratas Hotel 2",
            "Puerto Iguazú",
            "Triple",
            "$8200",
            LocalDate.of(2022, 02, 10),
            LocalDate.of(2022, 03, 20),
            false);

    Hotel hotel3 = new Hotel(
            "HB-0001",
            "Hotel Bristol",
            "Buenos Aires",
            "Single",
            "$5435",
            LocalDate.of(2022, 02, 10),
            LocalDate.of(2022, 03, 23),
            false);

    public List<Hotel> dataHotels() {
        hotels.add(hotel1);
        hotels.add(hotel2);
        hotels.add(hotel3);
        return hotels;
    }

    // no guarda nada en la lista. Basicamente, no anda.
    public List<Hotel> getHotelDisponible(String dateFrom, String dateTo, String destination) {
        List<Hotel> hotelesDisponibles = new ArrayList<>();
        LocalDate fechaComoLocalDateFrom = LocalDate.parse(dateFrom);
        LocalDate fechaComoLocalDateTo = LocalDate.parse(dateTo);
        for (Hotel hotel : hotels) {
            if (hotel.getCity().toUpperCase().contains(destination.toUpperCase()) &&
                    hotel.isReserved() &&
                    fechaComoLocalDateFrom.isAfter(hotel.getAvailabilityFrom()) &&
                    fechaComoLocalDateTo.isBefore(hotel.getAvailabilityUntil())) {

                hotelesDisponibles.add(hotel);

            }
            System.out.println(hotelesDisponibles);
        }
        return hotelesDisponibles;

    }
}
