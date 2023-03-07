package com.sprint1.AgenciaDeTurismo.Service;

import com.sprint1.AgenciaDeTurismo.DTO.RequestDto.Hotel.*;
import com.sprint1.AgenciaDeTurismo.DTO.RequestDto.PaymentMethodDto;
import com.sprint1.AgenciaDeTurismo.DTO.RequestDto.PeopleDto;
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
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;


@Service
public class HotelService {
    @Autowired
    HotelRepository hotelRepository;

    // US 0001
    public List<HotelModel> findAll() {
        return hotelRepository.dataHotels();
    }

    // US 0002
    public List<HotelModel> getHotelDisponibles(String dateFrom, String dateTo, String destination) {
        return hotelRepository.getHotelDisponible(dateFrom, dateTo, destination);
    }

    // US 0003
    public BookingResponse reserva(@RequestBody BookingRequestDto bookingRequestDto) {

        if (hotelRepository.dataHotels().isEmpty()) {
            throw new NotFoundException("No se econtraron hoteles disponibles");
        }

        BookingResponse response = new BookingResponse();
        BookingResponseDto bookingResponse = new BookingResponseDto();

        HotelModel bookHotel = hotelRepository.findHotelWhitCode(bookingRequestDto.getBooking().getHotelCode());

        if (bookHotel == null) {
            throw new NotFoundException("No se encuentra hotel con ese código");
        }

        List<HotelModel> reservationTrue = hotelRepository.getHotelDisponible(bookingRequestDto.getBooking().getDateFrom(),
                bookingRequestDto.getBooking().getDateTo(), bookingRequestDto.getBooking().getDestination());
        if (reservationTrue.size() == 0) {
            throw new BadRequestException("Las fechas solicitadas no están disponibles");
        }

        if (bookingRequestDto.getBooking().getRoomType().equalsIgnoreCase(bookHotel.getTypeRoom()) == false) {
            throw new NotFoundException("Ese tipo de habitacion no esta disponible.");
        }

        PeopleDto personData = bookingRequestDto.getBooking().getPeople();

        if(bookingRequestDto.getUserName() == null){
            throw new BadRequestException("Debes ingresar el userName");
        }
        if(bookingRequestDto.getUserName().length()< 5){
            throw new BadRequestException("El userName debe tener al menos 5 caracteres");
        }

        if(personData.getMail() == null || personData.getDni() == null || personData.getName() == null ||
                personData.getLastName() == null || personData.getBirthDate() == null || personData.getMail().length() < 10  ||
                personData.getDni().length() < 4 || personData.getName().length() < 3 ||
                personData.getLastName().length() < 4 || personData.getBirthDate().length() < 8) {
            throw new BadRequestException("Debes ingresar correctamente los datos del huesped");
        }
        PaymentMethodDto paymentData = bookingRequestDto.getBooking().getPaymentMethod();
        if(paymentData.getType() == null || (paymentData.getDues() == null || paymentData.getDues()< 1) || paymentData.getNumber() == null ){
            throw new PaymentRequiredException("Debes ingresar un metodo de pago valido.");
        }

        if(paymentData.getType().equalsIgnoreCase("credit") == false && paymentData.getType().equalsIgnoreCase("debit") == false  ){
            throw new PaymentRequiredException("No se permite este metodo de pago " + paymentData.getType());
        }

        if(bookingRequestDto.getBooking().getPeopleAmount() < 1){
            throw new BadRequestException("Debes ingresar la cantidad de huespedes.");
        }

        bookingResponse.setDateFrom(bookingRequestDto.getBooking().getDateFrom());
        bookingResponse.setDateTo(bookingRequestDto.getBooking().getDateTo());
        bookingResponse.setDestination(bookingRequestDto.getBooking().getDestination());
        bookingResponse.setHotelCode(bookingRequestDto.getBooking().getHotelCode());
        bookingResponse.setPeopleAmount(bookingRequestDto.getBooking().getPeopleAmount());
        bookingResponse.setRoomType(bookingRequestDto.getBooking().getRoomType());
        bookingResponse.setPeople(bookingRequestDto.getBooking().getPeople());

        LocalDate fechaComoLocalDateFrom = LocalDate.parse(bookingRequestDto.getBooking().getDateFrom());
        LocalDate fechaComoLocalDateTo = LocalDate.parse(bookingRequestDto.getBooking().getDateTo());

        Integer totalPrice = Math.toIntExact(ChronoUnit.DAYS.between(fechaComoLocalDateFrom, fechaComoLocalDateTo) * bookHotel.getPriceForNight());

        response.setUserName(bookingRequestDto.getUserName());
        response.setTotal(totalPrice);
        response.setBooking(bookingResponse);
        response.setStatusCode(new StatusCodeDto(200, "Proceso termino satisfactoriamente"));

        return response;
    }


}

