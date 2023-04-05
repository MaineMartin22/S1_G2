package com.sprint1.AgenciaDeTurismo.Repository;


import com.sprint1.AgenciaDeTurismo.Entity.ReservationFlight;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IReservationFlight extends JpaRepository<ReservationFlight, Integer> {
    //parte indivudal
    List<ReservationFlight> findReservationByTotal(Double desde, Double hasta);
}
