package com.sprint1.AgenciaDeTurismo.integration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.sprint1.AgenciaDeTurismo.DTO.FlightDto;
import com.sprint1.AgenciaDeTurismo.DTO.TouristicPackageDTO;
import com.sprint1.AgenciaDeTurismo.Entity.TouristicPackage;
import com.sprint1.AgenciaDeTurismo.utils.Flight.FlightDTOFactory;
import com.sprint1.AgenciaDeTurismo.utils.TouristicPackage.TouristicDTOFactory;
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

import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
public class TouristicControllerTest {
    @Autowired
    MockMvc mockMvc;

    ObjectWriter writer;

    @BeforeEach
    public void setupBeforeAll(){
        writer = new ObjectMapper()//Permite traducir un objeto nuestro a un string plano en JSON.
                .registerModule(new JavaTimeModule())
                .writer();
    }

    @Test
    public void saveEntity() throws Exception {

    }



    @Test
    void getAllEntities() throws Exception {
        // arrange
        List<TouristicPackageDTO> expected = List.of(TouristicDTOFactory.getPaquete1DTO(),
                TouristicDTOFactory.getPaquete2DTO());

        // REQUEST con  MockHttpServletRequestBuilder & MockMvcRequestBuilders
        // aca vamos a declarar la request que vamos a llamar o hacer
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/api/v1/touristic-packages");


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
}
