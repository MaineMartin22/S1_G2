package com.sprint1.AgenciaDeTurismo.Repository;


import com.sprint1.AgenciaDeTurismo.Entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


public interface IFlightRepository extends JpaRepository<Flight, Integer> {

    // MÉTODO NOMBRADO
    List<Flight> findFlightByDateFromAndDateToAndOriginAndDestiny(LocalDate dateFrom, LocalDate dateTo, String origin, String destiny);

    Optional<Flight> findFlightByNumberFlight(String code);
}
