package com.sprint1.AgenciaDeTurismo.Service;

import com.sprint1.AgenciaDeTurismo.DTO.ErrorDTO;
import com.sprint1.AgenciaDeTurismo.DTO.HotelDTO;
import com.sprint1.AgenciaDeTurismo.DTO.RequestDto.Hotel.*;
import com.sprint1.AgenciaDeTurismo.DTO.RequestDto.PaymentMethodDto;
import com.sprint1.AgenciaDeTurismo.DTO.ResponseDto.Hotel.BookingResponse;
import com.sprint1.AgenciaDeTurismo.DTO.ResponseDto.Hotel.BookingResponseDto;
import com.sprint1.AgenciaDeTurismo.Entity.*;
import com.sprint1.AgenciaDeTurismo.Exception.BadRequestException;
import com.sprint1.AgenciaDeTurismo.Exception.NotFoundException;
import com.sprint1.AgenciaDeTurismo.Repository.IBookingHotel;
import com.sprint1.AgenciaDeTurismo.Repository.IHotelRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class HotelService implements IHotelService {
    @Autowired
    IHotelRepository hotelRepository;
    @Autowired
    IBookingHotel bookingHotel;

    ModelMapper mapper = new ModelMapper();

    @Override
    public HotelDTO saveEntity(HotelDTO objectDTO) {
        // mappear de dto a entity para llevar al repo
        var entity = mapper.map(objectDTO, Hotel.class);
        // guardar
        hotelRepository.save(entity);
        // mappear de entity a dto para llevar al controller

        return mapper.map(entity, HotelDTO.class);
    }

    @Override
    public List<HotelDTO> getAllEntities() {
        // buscar todos los resultados en el repo
        var list = hotelRepository.findAll();
        // luego convertir de entidad a DTO
        return list.stream().map(
                        hotel -> mapper.map(hotel, HotelDTO.class)
                )
                .collect(Collectors.toList());
    }
    @Override
    public List<BookingResponse> getAllEntitiesResponse() {
        // buscar todos los resultados en el repo
        var list = bookingHotel.findAll();
        // luego convertir de entidad a DTO
        return list.stream().map(
                        booking -> mapper.map(booking, BookingResponse.class)
                )
                .collect(Collectors.toList());
    }

    @Override
    public HotelDTO getEntityByCode(String code) {
        Hotel hotel = hotelRepository.findHotelByHotelCode(code)
                .orElseThrow(() -> {
                    throw new NotFoundException("No se encuentra hotel con ese código");
                });
        // mapeo de entidad a dto.
        return mapper.map(hotel, HotelDTO.class);
    }

    @Override
    public ErrorDTO deleteEntity(String code) {
        Hotel hotel = hotelRepository.findHotelByHotelCode(code)
                .orElseThrow(() -> {
                    throw new NotFoundException("No se encuentra hotel con ese código");
                });

        hotelRepository.delete(hotel);

        return ErrorDTO.builder()
                .explanation("ELIMINACIÓN")
                .messages(List.of("Se elimino el hotel con código " + code))
                .build();
    }
    @Override
    public ErrorDTO deleteEntity(Integer id) {
        BookingHotel booking = bookingHotel.findById(id)
                .orElseThrow(() -> {
                    throw new NotFoundException("No se encontró ninguna reserva con ese id");
                });

        bookingHotel.delete(booking);

        return ErrorDTO.builder()
                .explanation("ELIMINACIÓN")
                .messages(List.of("Se elimino la reserva con id " + id))
                .build();
    }

    public List<HotelDTO> findHotelAvailable(LocalDate dateFrom, LocalDate dateTo, String destination) {
        if (dateFrom == null && dateTo == null && destination == null) {
            return getAllEntities();
        }


        var list = hotelRepository.findHotelByAvailabilityFromBeforeAndAvailabilityUntilAfterAndCity( dateFrom.plusDays(1),  dateTo.minusDays(1), destination);


        if (list.isEmpty())
            throw new NotFoundException("NO se encontró vuelos con esos datos");

        return list.stream().map(
                hotel -> mapper.map(hotel, HotelDTO.class)
        ).collect(Collectors.toList());
    }

    @Override
    public BookingResponse reservationHotel(BookingRequestDto bookingRequestDto) {
        if (getAllEntities().isEmpty()) {
            throw new NotFoundException("No se encontraron hoteles disponibles");
        }
        BookingHotel response = new BookingHotel();

        HotelDTO bookHotel = getEntityByCode(bookingRequestDto.getBooking().getHotelCode());

        if (!bookingRequestDto.getBooking().getRoomType().equalsIgnoreCase(bookHotel.getTypeRoom())) {
            throw new NotFoundException("Ese tipo de habitación no está disponible. \nLas habitaciones disponibles son : " + bookHotel.getTypeRoom());
        }

        if( (bookingRequestDto.getBooking().getRoomType().equalsIgnoreCase("Single") &&  bookingRequestDto.getBooking().getPeopleAmount() > 1) ||
                (bookingRequestDto.getBooking().getRoomType().equalsIgnoreCase("Doble") &&  bookingRequestDto.getBooking().getPeopleAmount() > 2) ||
                (bookingRequestDto.getBooking().getRoomType().equalsIgnoreCase("Triple") &&  bookingRequestDto.getBooking().getPeopleAmount() > 3)){
            throw new BadRequestException("La cantidad de personas excede la capacidad de la habitación");
        }

        List<Hotel> reservationTrue = hotelRepository.findHotelByAvailabilityFromBeforeAndAvailabilityUntilAfterAndCity(bookingRequestDto.getBooking().getDateFrom(),
                bookingRequestDto.getBooking().getDateTo(), bookingRequestDto.getBooking().getDestination());
        if (reservationTrue.isEmpty()) {
            throw new BadRequestException("Las fechas solicitadas no están disponibles");
        }

        var bookingResponse = mapper.map(bookingRequestDto.getBooking(), BookingHotelDetails.class);

        PaymentMethodDto paymentMethod = bookingRequestDto.getBooking().getPaymentMethod();

        double totalPrice = Math.round(ChronoUnit.DAYS.between(bookingRequestDto.getBooking().getDateFrom(), bookingRequestDto.getBooking().getDateTo()))*  bookHotel.getPriceForNight();

        var totalIntereses = CalcularInteres.calculateInterest(paymentMethod, totalPrice);

        double totalFinal = totalPrice + totalIntereses;

        response.setUserName(bookingRequestDto.getUserName());
        response.setTotalNeto(totalPrice);
        response.setTotalIntereses(totalIntereses);
        response.setTotalFinal(totalFinal);
        response.setBooking(bookingResponse);

        bookingHotel.save(response);
        return mapper.map(response, BookingResponse.class);
    }
}





