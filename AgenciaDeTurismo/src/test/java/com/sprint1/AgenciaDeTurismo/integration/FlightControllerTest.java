package com.sprint1.AgenciaDeTurismo.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.sprint1.AgenciaDeTurismo.DTO.*;
import com.sprint1.AgenciaDeTurismo.utils.Data.DestinoMasSolicitadoDTOFactory;
import com.sprint1.AgenciaDeTurismo.utils.Data.ErrorDTOFactory;
import com.sprint1.AgenciaDeTurismo.utils.Data.GananciasDTOFactory;
import com.sprint1.AgenciaDeTurismo.utils.Flight.FlightDTOFactory;
import com.sprint1.AgenciaDeTurismo.utils.Hotel.HotelDTOFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
class FlightControllerTest {
    @Autowired
    MockMvc mockMvc;

    ObjectWriter writer = new ObjectMapper()
            .registerModule(new JavaTimeModule()) // convertir fechas
            .writer();
    List<FlightDto> flights;

    @BeforeEach
    void setup() {
        // Inicializar el estado antes de cada test
        flights = List.of(FlightDTOFactory.getBsAsPuertoIguazuDTO(), FlightDTOFactory.getPuertoIguazuBogotaDTO());
    }

    @AfterEach
    void teardown() {
        // Limpiar el estado después de cada test
        flights = null;
    }

    @Test
    void flightAvailability() throws Exception{
        List<FlightDto> expected = List.of(FlightDTOFactory.getBsAsPuertoIguazuDTO());

        LocalDate dateFrom = LocalDate.of(2022, 02, 10);
        LocalDate dateTo = LocalDate.of(2022, 02, 15);
        String origin = "Buenos Aires";
        String destiny = "Puerto Iguazú";

        // REQUEST con  MockHttpServletRequestBuilder & MockMvcRequestBuilders
        // aca vamos a declarar la request que vamos a llamar o hacer
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/api/v1/flights")
                .param("dateFrom", dateFrom.toString())
                .param("dateTo", dateTo.toString())
                .param("origin", origin)
                .param("destiny", destiny);


        // Los 3 EXPECTED con ResultMatcher & MockMvcResultMatchers --
        // STATUS
        ResultMatcher statusExpected = MockMvcResultMatchers.status().isOk();

        // BODY
        ResultMatcher bodyExpected = MockMvcResultMatchers.content().json(
                writer.writeValueAsString(expected)
        );

        // CONTENT-TYPE
        ResultMatcher contentTypeExpected = MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON);

        // act & assert con mockmvc

        mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.print())
                .andExpectAll(statusExpected,
                        bodyExpected,
                        contentTypeExpected);
    }

    @Test
    void flightAll() throws Exception{
        // arrange
        List<FlightDto> expected = List.of(FlightDTOFactory.getBsAsPuertoIguazuDTO(),
                FlightDTOFactory.getPuertoIguazuBogotaDTO());

        // REQUEST con  MockHttpServletRequestBuilder & MockMvcRequestBuilders
        // aca vamos a declarar la request que vamos a llamar o hacer
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/api/v1/flights");


        // Los 3 EXPECTED con ResultMatcher & MockMvcResultMatchers --
        // STATUS
        ResultMatcher statusExpected = MockMvcResultMatchers.status().isOk();

        // BODY
        ResultMatcher bodyExpected = MockMvcResultMatchers.content().json(
                writer.writeValueAsString(expected)
        );

        // CONTENT-TYPE
        ResultMatcher contentTypeExpected = MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON);

        // act & assert con mockmvc

        mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.print())
                .andExpectAll(statusExpected,
                        bodyExpected,
                        contentTypeExpected);
    }




    @Test
    void saveEntity()throws Exception {
    }

    @Test
    void flightReservation() throws Exception{
    }

    @Test
    void findFlightByCode()throws Exception {
    }



    @Test
    void deleteReservationByID()throws Exception {
    }

    @Test
    void reservasEnLaDB()throws Exception {
    }

    @Test
    void updateFlight() throws Exception{
    }

    @Test
    void updateReservaFlight() {
    }

    @Test
    void getGananciasTotales() throws Exception {
        // arrange
        GananciasDTO expected = GananciasDTOFactory.gananciasVuelos();

        // REQUEST con  MockHttpServletRequestBuilder & MockMvcRequestBuilders
        // aca vamos a declarar la request que vamos a llamar o hacer
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/api/v1/flight-reservations/totalGanancias");


        // Los 3 EXPECTED con ResultMatcher & MockMvcResultMatchers --
        // STATUS
        ResultMatcher statusExpected = MockMvcResultMatchers.status().isOk();

        // BODY
        ResultMatcher bodyExpected = MockMvcResultMatchers.content().json(
                writer.writeValueAsString(expected)
        );

        // CONTENT-TYPE
        ResultMatcher contentTypeExpected = MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON);

        // act & assert con mockmvc

        mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(statusExpected)
                .andExpect(bodyExpected)
                .andExpect(contentTypeExpected);
    }
    @Test
    void getDestinoMasSolicitado() throws Exception {
        // arrange
        DestinoMasSolicitado expected = DestinoMasSolicitadoDTOFactory.destinoMasSolicitadoHoteles();

        // REQUEST con  MockHttpServletRequestBuilder & MockMvcRequestBuilders
        // aca vamos a declarar la request que vamos a llamar o hacer
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/api/v1/flight-reservations/destinoMasSolicitado");


        // Los 3 EXPECTED con ResultMatcher & MockMvcResultMatchers --
        // STATUS
        ResultMatcher statusExpected = MockMvcResultMatchers.status().isOk();

        // BODY
        ResultMatcher bodyExpected = MockMvcResultMatchers.content().json(
                writer.writeValueAsString(expected)
        );

        // CONTENT-TYPE
        ResultMatcher contentTypeExpected = MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON);

        // act & assert con mockmvc

        mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(statusExpected)
                .andExpect(bodyExpected)
                .andExpect(contentTypeExpected);
    }
}