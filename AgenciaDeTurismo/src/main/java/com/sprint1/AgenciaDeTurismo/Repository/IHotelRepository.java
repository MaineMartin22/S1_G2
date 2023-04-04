package com.sprint1.AgenciaDeTurismo.Repository;

import com.sprint1.AgenciaDeTurismo.Entity.Flight;
import com.sprint1.AgenciaDeTurismo.Entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface IHotelRepository extends JpaRepository<Hotel, Integer> {
    List<Hotel>findHotelByAvailabilityFromBeforeAndAvailabilityUntilAfterAndCity(LocalDate availabilityFrom, LocalDate availabilityUntil, String city);

    List<Hotel>findHotelByCity(String city);


    Optional<Hotel> findHotelByHotelCode(String code);
}