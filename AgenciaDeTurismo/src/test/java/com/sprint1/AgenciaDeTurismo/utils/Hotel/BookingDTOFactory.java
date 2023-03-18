package com.sprint1.AgenciaDeTurismo.utils.Hotel;

import com.sprint1.AgenciaDeTurismo.DTO.RequestDto.Hotel.BookingDto;
import com.sprint1.AgenciaDeTurismo.DTO.RequestDto.PaymentMethodDto;
import com.sprint1.AgenciaDeTurismo.DTO.RequestDto.PeopleDto;
import com.sprint1.AgenciaDeTurismo.utils.Data.PaymentMethodDTOFactory;
import com.sprint1.AgenciaDeTurismo.utils.Data.PeopleDTOFactory;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

public class BookingDTOFactory {
    public static BookingDto bookingDtoPuertoIguazuDoblegetRefused(){
        return BookingDto.builder()
                .dateFrom(LocalDate.of(2022, 02, 10))
                .dateTo(LocalDate.of(2022, 03, 20))
                .destination("Puerto Iguazú")
                .hotelCode("CH-0002")
                .peopleAmount(2)
                .roomType("Doble")
                .people(
                        List.of(
                                PeopleDTOFactory.getPeopleOne(),
                                PeopleDTOFactory.getPeopleTwo()
                        )
                )
                .paymentMethod(PaymentMethodDTOFactory.getPaymentCreditRefused())
                .build();
    }
    public static BookingDto bookingDtoPuertoIguazuDobleDebit(){
        return BookingDto.builder()
                .dateFrom(LocalDate.of(2022, 02, 10))
                .dateTo(LocalDate.of(2022, 03, 20))
                .destination("Puerto Iguazú")
                .hotelCode("CH-0002")
                .peopleAmount(2)
                .roomType("Doble")
                .people(
                        List.of(
                                PeopleDTOFactory.getPeopleOne(),
                                PeopleDTOFactory.getPeopleTwo()
                        )
                )
                .paymentMethod(PaymentMethodDTOFactory.getPaymentDebit())
                .build();
    }
    public static BookingDto bookingDtoBuenosAiresSingleThreeDues(){
        return BookingDto.builder()
                .dateFrom(LocalDate.of(2022, 02, 10))
                .dateTo(LocalDate.of(2022, 02, 20))
                .destination("Buenos Aires")
                .hotelCode("HB-0001")
                .peopleAmount(1)
                .roomType("Single")
                .people(
                        List.of(
                                PeopleDTOFactory.getPeopleThree()
                        )
                )
                .paymentMethod(PaymentMethodDTOFactory.getPaymentCreditThreeDues())
                .build();
    }
    public static BookingDto bookingDtoBuenosAiresSingleSixeDues(){
        return BookingDto.builder()
                .dateFrom(LocalDate.of(2022, 02, 10))
                .dateTo(LocalDate.of(2022, 02, 20))
                .destination("Buenos Aires")
                .hotelCode("HB-0001")
                .peopleAmount(1)
                .roomType("Single")
                .people(
                        List.of(
                                PeopleDTOFactory.getPeopleThree()
                        )
                )
                .paymentMethod(PaymentMethodDTOFactory.getPaymentCreditSixDues())
                .build();
    }
    public static BookingDto bookingDtoBuenosAiresSingleTwelveDues(){
        return BookingDto.builder()
                .dateFrom(LocalDate.of(2022, 02, 10))
                .dateTo(LocalDate.of(2022, 02, 20))
                .destination("Buenos Aires")
                .hotelCode("HB-0001")
                .peopleAmount(1)
                .roomType("Single")
                .people(
                        List.of(
                                PeopleDTOFactory.getPeopleThree()
                        )
                )
                .paymentMethod(PaymentMethodDTOFactory.getPaymentCreditTwelveDues())
                .build();
    }

    public static BookingDto getNoHotelesDisponibles(){
        return BookingDto.builder()
                .dateFrom(LocalDate.of(2023, 02, 10))
                .dateTo(LocalDate.of(2023, 02, 20))
                .destination("Brasilia")
                .hotelCode("HB-0001")
                .peopleAmount(2)
                .roomType("Doble")
                .people(
                        List.of(
                                PeopleDTOFactory.getPeopleThree(),
                                PeopleDTOFactory.getPeopleTwo()
                        )
                )
                .paymentMethod(PaymentMethodDTOFactory.getPaymentCreditTwelveDues())
                .build();
    }
}

