package com.sprint1.AgenciaDeTurismo.Repository;


import com.sprint1.AgenciaDeTurismo.Entity.ReservationFlight;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IReservationFlight extends JpaRepository<ReservationFlight, Integer> {
}
