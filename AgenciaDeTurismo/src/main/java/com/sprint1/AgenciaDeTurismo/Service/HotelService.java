package com.sprint1.AgenciaDeTurismo.Service;

import com.sprint1.AgenciaDeTurismo.DTO.HotelDTO;
import com.sprint1.AgenciaDeTurismo.DTO.RequestDto.Hotel.*;
import com.sprint1.AgenciaDeTurismo.DTO.RequestDto.PaymentMethodDto;
import com.sprint1.AgenciaDeTurismo.DTO.ResponseDto.Hotel.BookingResponse;
import com.sprint1.AgenciaDeTurismo.DTO.ResponseDto.Hotel.BookingResponseDto;
import com.sprint1.AgenciaDeTurismo.DTO.StatusCodeDto;
import com.sprint1.AgenciaDeTurismo.Exception.BadRequestException;
import com.sprint1.AgenciaDeTurismo.Exception.NotFoundException;
import com.sprint1.AgenciaDeTurismo.Exception.PaymentRequiredException;
import com.sprint1.AgenciaDeTurismo.Model.HotelModel;
import com.sprint1.AgenciaDeTurismo.Repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;


@Service
public class HotelService implements IHotelService {
    @Autowired
    HotelRepository hotelRepository;

    // US 0001

    @Override
    public List<HotelDTO> findAll() {
        return hotelRepository.dataHotels();
    }

    // US 0002
    public List<HotelDTO> getHotelDisponibles(LocalDate dateFrom, LocalDate dateTo, String destination) {
        if (dateFrom == null && dateTo == null && destination == null) {
            return findAll();
        }

        List<HotelDTO> hotelDisponible = hotelRepository.getHotelDisponible(dateFrom, dateTo, destination);

        if (hotelDisponible.isEmpty()) {
            throw new NotFoundException("No se encontraron hoteles con esos datos");

        }
        if (!isSameDestination(destination)){
            throw new BadRequestException("No se encuentran hoteles en ese destino");

        }

        return hotelDisponible;
    }
    private boolean isSameDestination(String destination){
        return findAll().stream().anyMatch(hotel -> hotel.getCity().equalsIgnoreCase(destination));
    }

    // US 0003
    public BookingResponse reservationHotel(BookingRequestDto bookingRequestDto) {

        if (hotelRepository.dataHotels().isEmpty()) {
            throw new NotFoundException("No se encontraron hoteles disponibles");
        }
        BookingResponse response = new BookingResponse();
        BookingResponseDto bookingResponse = new BookingResponseDto();

        HotelModel bookHotel = hotelRepository.findHotelWhitCode(bookingRequestDto.getBooking().getHotelCode());
        if (bookHotel == null) {
            throw new NotFoundException("No se encuentra hotel con ese código");
        }
        if (!bookingRequestDto.getBooking().getRoomType().equalsIgnoreCase(bookHotel.getTypeRoom())) {
            throw new NotFoundException("Ese tipo de habitación no está disponible. \nLas habitaciones disponibles es : " + bookHotel.getTypeRoom());
        }

        if( (bookingRequestDto.getBooking().getRoomType().equalsIgnoreCase("Single") &&  bookingRequestDto.getBooking().getPeopleAmount() > 1) ||
                (bookingRequestDto.getBooking().getRoomType().equalsIgnoreCase("Doble") &&  bookingRequestDto.getBooking().getPeopleAmount() > 2) ||
                (bookingRequestDto.getBooking().getRoomType().equalsIgnoreCase("Triple") &&  bookingRequestDto.getBooking().getPeopleAmount() > 3)){
            throw new BadRequestException("La cantidad de personas excede la capacidad de la habitación");
        }


        List<HotelDTO> reservationTrue = hotelRepository.getHotelDisponible(bookingRequestDto.getBooking().getDateFrom(),
                bookingRequestDto.getBooking().getDateTo(), bookingRequestDto.getBooking().getDestination());
        if (reservationTrue.isEmpty()) {
            throw new BadRequestException("Las fechas solicitadas no están disponibles");
        }


        PaymentMethodDto paymentData = bookingRequestDto.getBooking().getPaymentMethod();

        if (!paymentData.getType().equalsIgnoreCase("credit") && !paymentData.getType().equalsIgnoreCase("debit")) {
            throw new PaymentRequiredException("El método de pago " + paymentData.getType() + " no esta disponible. Solo se permite tarjetas de crédito o débito" );
        }

        bookingResponse.setDateFrom(bookingRequestDto.getBooking().getDateFrom());
        bookingResponse.setDateTo(bookingRequestDto.getBooking().getDateTo());
        bookingResponse.setDestination(bookingRequestDto.getBooking().getDestination());
        bookingResponse.setHotelCode(bookingRequestDto.getBooking().getHotelCode());
        bookingResponse.setPeopleAmount(bookingRequestDto.getBooking().getPeopleAmount());
        bookingResponse.setRoomType(bookingRequestDto.getBooking().getRoomType());
        bookingResponse.setPeople(bookingRequestDto.getBooking().getPeople());


        double totalPrice = Math.toIntExact(ChronoUnit.DAYS.between(bookingRequestDto.getBooking().getDateFrom(), bookingRequestDto.getBooking().getDateTo()) * bookHotel.getPriceForNight());
        double totalIntereses = 0;


        if (paymentData.getType().equalsIgnoreCase("credit") && paymentData.getDues() <= 3){
            totalIntereses = totalPrice * 0.05;
        } else if (paymentData.getType().equalsIgnoreCase("credit") && paymentData.getDues() <= 6) {
            totalIntereses = totalPrice * 0.10;
        } else if(paymentData.getType().equalsIgnoreCase("credit") && paymentData.getDues() <= 12){
            totalIntereses = totalPrice * 0.15;
        } else if ((paymentData.getType().equalsIgnoreCase("credit") && paymentData.getDues() > 12)){
            throw new PaymentRequiredException("Las cuotas con tarjeta de crédito no pueden ser mayor a 12");
        }

        if ((paymentData.getType().equalsIgnoreCase("debit") && paymentData.getDues() != 1)){
            throw new PaymentRequiredException("Solo se permite el pago en una sola cuota");
        }

        double totalFinal = totalPrice + totalIntereses;

        response.setUserName(bookingRequestDto.getUserName());
        response.setTotalNeto(totalPrice);
        response.setTotalIntereses(totalIntereses);
        response.setTotalFinal(totalFinal);
        response.setBooking(bookingResponse);
        response.setStatusCode(new StatusCodeDto(200, "Proceso termino satisfactoriamente"));

        return response;
    }

}

