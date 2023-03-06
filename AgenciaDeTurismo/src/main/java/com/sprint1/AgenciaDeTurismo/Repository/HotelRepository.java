package com.sprint1.AgenciaDeTurismo.Repository;

import com.sprint1.AgenciaDeTurismo.DTO.HotelDTO;
import com.sprint1.AgenciaDeTurismo.Model.Hotel;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.SQLOutput;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class HotelRepository {

    private List<Hotel> hotels = new ArrayList<>();
    private List<HotelDTO> hotelDTO = new ArrayList<>();

    Hotel hotel1 = new Hotel("CH-0002", "Cataratas Hotel", "Puerto Iguazú", "Doble", "$6300", LocalDate.of(2022, 02, 10), LocalDate.of(2022, 03, 20), false);
    Hotel hotel2 = new Hotel("CH-0003", "Cataratas Hotel 2", "Puerto Iguazú", "Triple", "$8200", LocalDate.of(2022, 02, 10), LocalDate.of(2022, 03, 20), false);
    Hotel hotel3 = new Hotel("HB-0001", "Hotel Bristol", "Buenos Aires", "Single", "$5435", LocalDate.of(2022, 02, 10), LocalDate.of(2022, 03, 23), false);
    Hotel hotel4 = new Hotel("BH,0002", "Hotel Bristol 2", "Buenos Aires", "Doble", "$7200", LocalDate.of(2022,02,12), LocalDate.of(2022,04,17), false);
    Hotel hotel5 = new Hotel("SH,0002", "Sheraton", "Tucumán", "Doble", "$5790", LocalDate.of(2022,04,17), LocalDate.of(2022,05,23), false);
    Hotel hotel6 = new Hotel("SH,0001", "Sheraton 2", "Tucumán", "Single", "$4150", LocalDate.of(2022,01,02), LocalDate.of(2022,02,19), false);
    Hotel hotel7 = new Hotel("SE,0001", "Selina", "Bogotá", "Single", "$3900", LocalDate.of(2022,01,23), LocalDate.of(2022,11,23), false);
    Hotel hotel8 = new Hotel("SE,0002", "Selina 2", "Bogotá", "Doble", " 5840", LocalDate.of(2022,01,23), LocalDate.of(2022,10,15), false);
    Hotel hotel9 = new Hotel  ("EC,0003", "El Campín", "Bogotá", "Triple", "$7020", LocalDate.of(2022,02,15), LocalDate.of(2022,03,27), false);
    Hotel hotel10 = new Hotel("CP,0004", "Central Plaza", "Medellín", "Múltiple", "$8600", LocalDate.of(2022,03,01), LocalDate.of(2022,04,17), false);
    Hotel hotel11 = new Hotel  ("CP,0002", "Central Plaza 2", "Medellín", "Doble", "$6400", LocalDate.of(2022,02,10), LocalDate.of(2022,03,20), false);
    Hotel hotel12 = new Hotel("BG,0004", "Bocagrande", "Cartagena", "Múltiple", "$9370", LocalDate.of(2022,04,17), LocalDate.of(2022,06,12), false);

    public List<Hotel> dataHotels() {
        hotels.add(hotel1);
        hotels.add(hotel2);
        hotels.add(hotel3);
        hotels.add(hotel4);
        hotels.add(hotel5);
        hotels.add(hotel6);
        hotels.add(hotel7);
        hotels.add(hotel8);
        hotels.add(hotel9);
        hotels.add(hotel10);
        hotels.add(hotel11);
        hotels.add(hotel12);
        return hotels;
    }

    public List<Hotel> getHotelDisponible(String dateFrom, String dateTo, String destination) {

        List<Hotel>  hotelesDisponibles = new ArrayList<>();
        LocalDate fechaComoLocalDateFrom = LocalDate.parse(dateFrom);
        LocalDate fechaComoLocalDateTo = LocalDate.parse(dateTo);

        for (Hotel hotel : dataHotels()) {
            if(
                    hotel.getCity().toUpperCase().contains(destination.toUpperCase()) &&
                    hotel.isReserved() == false &&
                    (fechaComoLocalDateFrom.isAfter(hotel.getAvailabilityFrom()) ||
                    fechaComoLocalDateFrom.getDayOfMonth() == hotel.getAvailabilityFrom().getDayOfMonth()) &&
                    (fechaComoLocalDateTo.isBefore(hotel.getAvailabilityUntil()) ||
                    fechaComoLocalDateTo.getDayOfMonth() ==  hotel.getAvailabilityUntil().getDayOfMonth()))
            {

                 hotelesDisponibles.add(hotel);
            }
        }
        return hotelesDisponibles;

    }
}
