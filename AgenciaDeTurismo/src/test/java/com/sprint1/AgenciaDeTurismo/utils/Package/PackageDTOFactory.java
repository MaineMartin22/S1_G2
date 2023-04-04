package com.sprint1.AgenciaDeTurismo.utils.Package;

import com.sprint1.AgenciaDeTurismo.DTO.PackageDTO;
import com.sprint1.AgenciaDeTurismo.Entity.Flight;
import com.sprint1.AgenciaDeTurismo.Entity.Hotel;
import com.sprint1.AgenciaDeTurismo.utils.Flight.FlightFactory;
import com.sprint1.AgenciaDeTurismo.utils.Hotel.HotelFactory;

import java.time.LocalDate;

public class PackageDTOFactory {
    public static PackageDTO getPackage1() {
        return PackageDTO.builder()
                .id(1)
                .codePackage("PACK-001")
                .price(50000.0)
                .city("Puerto Iguazú")
                .flight(getBsAsPuertoIguazuPack())
                .hotel(getCataratasHotelPack())
                .build();
    }

    public static PackageDTO getPackage2() {
        return PackageDTO.builder()
                .id(2)
                .codePackage("PACK-002")
                .price(60000.0)
                .city("Puerto Iguazú")
                .flight(getBsAsPuertoIguazuPack())
                .hotel(getBristolPack())
                .build();
    }

    public static PackageDTO getPackage2edit() {
        return PackageDTO.builder()
                .id(2)
                .codePackage("PACK-200")
                .price(65000.0)
                .city("Puerto Iguazú")
                .flight(getBsAsPuertoIguazuPack())
                .hotel(getBristolPack())
                .build();
    }

    public static PackageDTO getPackage2editado() {
        return PackageDTO.builder()
                .id(2)
                .codePackage("PACK-200")
                .price(65000.0)
                .city("Puerto Iguazú")
                .flight(getBsAsPuertoIguazuPack())
                .hotel(getBristolPack())
                .build();
    }

    public static PackageDTO getPackage3() {
        return PackageDTO.builder()
                .id(3)
                .codePackage("PACK-003")
                .price(90000.0)
                .city("Miami")
                .flight(FlightFactory.getBsAsPuertoIguazu())
                .hotel(HotelFactory.getBristol())
                .build();
    }

    public static PackageDTO getPackage3new() {
        return PackageDTO.builder()
                .codePackage("PACK-003")
                .price(90000.0)
                .city("Miami")
                .flight(FlightFactory.getBsAsPuertoIguazu())
                .hotel(HotelFactory.getBristol())
                .build();
    }

    public static Flight getBsAsPuertoIguazuPack(){
        return Flight.builder()
                .id(1)
                .numberFlight("BAPI-1235")
                .origin("Buenos Aires")
                .destiny("Puerto Iguazú")
                .seatType("Economy")
                .priceForPerson(6500.0)
                .dateFrom(LocalDate.of(2022,02,10))
                .dateTo(LocalDate.of(2022,02,15))
                .build();
    }

    public static Hotel getCataratasHotelPack() {
        return Hotel.builder().hotelCode("CH-0002")
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

    public static Hotel getBristolPack() {
        return Hotel.builder().hotelCode("HB-0001")
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

}
