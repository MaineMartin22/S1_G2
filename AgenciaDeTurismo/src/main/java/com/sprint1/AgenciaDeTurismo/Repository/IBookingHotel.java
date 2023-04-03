package com.sprint1.AgenciaDeTurismo.Repository;

import com.sprint1.AgenciaDeTurismo.Entity.BookingHotel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IBookingHotel extends JpaRepository<BookingHotel, Integer> {

}
