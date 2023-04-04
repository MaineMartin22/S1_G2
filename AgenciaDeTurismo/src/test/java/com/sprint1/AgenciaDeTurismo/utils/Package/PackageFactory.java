package com.sprint1.AgenciaDeTurismo.utils.Package;


import com.sprint1.AgenciaDeTurismo.DTO.PackageDTO;
import com.sprint1.AgenciaDeTurismo.Entity.Package;
import com.sprint1.AgenciaDeTurismo.utils.Flight.FlightFactory;
import com.sprint1.AgenciaDeTurismo.utils.Hotel.HotelFactory;

public class PackageFactory {
    public static Package getPackage1() {
        return Package.builder()
                .id(1)
                .codePackage("PACK-001")
                .price(50000.0)
                .city("Puerto Iguazú")
                .flight(FlightFactory.getBsAsPuertoIguazu())
                .hotel(HotelFactory.getCataratasHotel())
                .build();
    }

    public static Package getPackage2() {
        return Package.builder()
                .id(2)
                .codePackage("PACK-002")
                .price(60000.0)
                .city("Puerto Iguazú")
                .flight(FlightFactory.getBsAsPuertoIguazu())
                .hotel(HotelFactory.getBristol())
                .build();
    }

    public static Package getPackage3() {
        return Package.builder()
                .id(3)
                .codePackage("PACK-003")
                .price(90000.0)
                .city("Miami")
                .flight(FlightFactory.getBsAsPuertoIguazu())
                .hotel(HotelFactory.getBristol())
                .build();
    }
}
