package com.sprint1.AgenciaDeTurismo.Repository;


import com.sprint1.AgenciaDeTurismo.DTO.HotelDTO;
import com.sprint1.AgenciaDeTurismo.Model.HotelModel;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class HotelRepository {

    private List<HotelModel> hotelModels = new ArrayList<>();
    private List<HotelDTO> hotelDTO = new ArrayList<>();

    HotelModel hotelModel1 = new HotelModel("CH-0002", "Cataratas Hotel", "Puerto Iguazú", "Doble", 6300, LocalDate.of(2022, 02, 10), LocalDate.of(2022, 03, 20), false);
    HotelModel hotelModel2 = new HotelModel("CH-0003", "Cataratas Hotel 2", "Puerto Iguazú", "Triple", 8200, LocalDate.of(2022, 02, 10), LocalDate.of(2022, 03, 20), false);
    HotelModel hotelModel3 = new HotelModel("HB-0001", "Hotel Bristol", "Buenos Aires", "Single", 5435, LocalDate.of(2022, 02, 10), LocalDate.of(2022, 03, 23), false);
    HotelModel hotelModel4 = new HotelModel("BH,0002", "Hotel Bristol 2", "Buenos Aires", "Doble", 7200, LocalDate.of(2022,02,12), LocalDate.of(2022,04,17), false);
    HotelModel hotelModel5 = new HotelModel("SH,0002", "Sheraton", "Tucumán", "Doble", 5790, LocalDate.of(2022,04,17), LocalDate.of(2022,05,23), false);
    HotelModel hotelModel6 = new HotelModel("SH,0001", "Sheraton 2", "Tucumán", "Single", 4150, LocalDate.of(2022,01,02), LocalDate.of(2022,02,19), false);
    HotelModel hotelModel7 = new HotelModel("SE,0001", "Selina", "Bogotá", "Single", 3900, LocalDate.of(2022,01,23), LocalDate.of(2022,11,23), false);
    HotelModel hotelModel8 = new HotelModel("SE,0002", "Selina 2", "Bogotá", "Doble", 5840, LocalDate.of(2022,01,23), LocalDate.of(2022,10,15), false);
    HotelModel hotelModel9 = new HotelModel("EC,0003", "El Campín", "Bogotá", "Triple", 7020, LocalDate.of(2022,02,15), LocalDate.of(2022,03,27), false);
    HotelModel hotelModel10 = new HotelModel("CP,0004", "Central Plaza", "Medellín", "Múltiple", 8600, LocalDate.of(2022,03,01), LocalDate.of(2022,04,17), false);
    HotelModel hotelModel11 = new HotelModel("CP,0002", "Central Plaza 2", "Medellín", "Doble", 6400, LocalDate.of(2022,02,10), LocalDate.of(2022,03,20), false);
    HotelModel hotelModel12 = new HotelModel("BG,0004", "Bocagrande", "Cartagena", "Múltiple", 9370, LocalDate.of(2022,04,17), LocalDate.of(2022,06,12), false);

    public List<HotelModel> dataHotels() {
        hotelModels.add(hotelModel1);
        hotelModels.add(hotelModel2);
        hotelModels.add(hotelModel3);
        hotelModels.add(hotelModel4);
        hotelModels.add(hotelModel5);
        hotelModels.add(hotelModel6);
        hotelModels.add(hotelModel7);
        hotelModels.add(hotelModel8);
        hotelModels.add(hotelModel9);
        hotelModels.add(hotelModel10);
        hotelModels.add(hotelModel11);
        hotelModels.add(hotelModel12);
        return hotelModels;
    }

    public List<HotelModel> getHotelDisponible(String dateFrom, String dateTo, String destination) {

        List<HotelModel>  hotelesDisponibles = new ArrayList<>();
        LocalDate fechaComoLocalDateFrom = LocalDate.parse(dateFrom);
        LocalDate fechaComoLocalDateTo = LocalDate.parse(dateTo);

        for (HotelModel hotelModel : dataHotels()) {
            if(
                    hotelModel.getCity().toUpperCase().contains(destination.toUpperCase()) &&
                    hotelModel.isReserved() == false &&
                    (fechaComoLocalDateFrom.isAfter(hotelModel.getAvailabilityFrom()) ||
                    fechaComoLocalDateFrom.getDayOfMonth() == hotelModel.getAvailabilityFrom().getDayOfMonth()) &&
                    (fechaComoLocalDateTo.isBefore(hotelModel.getAvailabilityUntil()) ||
                    fechaComoLocalDateTo.getDayOfMonth() ==  hotelModel.getAvailabilityUntil().getDayOfMonth()))
            {

                 hotelesDisponibles.add(hotelModel);
            }
        }
        return hotelesDisponibles;
    }

    public HotelModel findHotelWhitCode(String code){
        return dataHotels().stream().filter(hotel -> hotel.getHotelCode().equalsIgnoreCase(code)).findFirst().orElse(null);
    }

}
