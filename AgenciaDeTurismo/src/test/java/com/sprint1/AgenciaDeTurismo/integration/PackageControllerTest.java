package com.sprint1.AgenciaDeTurismo.integration;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.sprint1.AgenciaDeTurismo.DTO.ErrorDTO;
import com.sprint1.AgenciaDeTurismo.DTO.FlightDto;
import com.sprint1.AgenciaDeTurismo.DTO.PackageDTO;
import com.sprint1.AgenciaDeTurismo.utils.Data.ErrorDTOFactory;
import com.sprint1.AgenciaDeTurismo.utils.Flight.FlightDTOFactory;
import com.sprint1.AgenciaDeTurismo.utils.Package.PackageDTOFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;



@SpringBootTest
@AutoConfigureMockMvc
class PackageControllerTest {

    @Autowired
    MockMvc mockMvc;

    ObjectWriter writer = new ObjectMapper()
            .registerModule(new JavaTimeModule()) // convertir fechas
            .writer();

    @Test
    @DirtiesContext
    void createPackage() throws Exception {
        // arrange
        PackageDTO expected = PackageDTOFactory.getPackage3();

        PackageDTO param = PackageDTOFactory.getPackage3new();

        // REQUEST con  MockHttpServletRequestBuilder & MockMvcRequestBuilders
        // aca vamos a declarar la request que vamos a llamar o hacer
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post("/api/v1/package/new")
                .content(
                        writer.writeValueAsString(param)
                )
                .contentType(MediaType.APPLICATION_JSON);



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
    @DirtiesContext
    void packageEnDB() throws Exception {
        // arrange
        List<PackageDTO>  expected = List.of(PackageDTOFactory.getPackage1(),
                PackageDTOFactory.getPackage2());

        // REQUEST con  MockHttpServletRequestBuilder & MockMvcRequestBuilders
        // aca vamos a declarar la request que vamos a llamar o hacer
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/api/v1/package/");


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
    @DirtiesContext
    void updatePackage() throws Exception{
        // arrange
        PackageDTO expected = PackageDTOFactory.getPackage2editado();

        PackageDTO param = PackageDTOFactory.getPackage2edit();

        // REQUEST con  MockHttpServletRequestBuilder & MockMvcRequestBuilders
        // aca vamos a declarar la request que vamos a llamar o hacer
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .put("/api/v1/package/edit")
                .param("codePackage", "PACK-002")
                .content(
                        writer.writeValueAsString(param)
                )
                .contentType(MediaType.APPLICATION_JSON);



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
    @DirtiesContext
    void deleteByCode() throws Exception {
        // arrange
        ErrorDTO expected = ErrorDTOFactory.deletePack1();

        // REQUEST con  MockHttpServletRequestBuilder & MockMvcRequestBuilders
        // aca vamos a declarar la request que vamos a llamar o hacer
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .delete("/api/v1/package/delete")
                .param("codePackage", "PACK-001");


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