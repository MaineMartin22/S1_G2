package com.sprint1.AgenciaDeTurismo.utils.Data;

import com.sprint1.AgenciaDeTurismo.DTO.RequestDto.PeopleDto;

public class PeopleDTOFactory {

    public static PeopleDto getPeopleOne(){
        return PeopleDto.builder()
                .id(1)
                .dni("125421")
                .name("martin")
                .lastName("maine")
                .birthDate("2022-02-02")
                .mail("mm@gmail.com")
                .build();
    }


    public static PeopleDto getPeopleTwo(){
        return PeopleDto.builder()
                .dni("29983746")
                .name("María")
                .lastName("López")
                .birthDate("10-03-1992")
                .mail("maria_lopez@gmail.com")
                .build();
    }
    public static PeopleDto getPeopleThree(){
        return PeopleDto.builder()
                .dni("47829173")
                .name("Juan")
                .lastName("Pérez")
                .birthDate("02-07-1985")
                .mail("juan_perez@hotmail.com")
                .build();
    }
    public static PeopleDto getPeopleFour(){
        return PeopleDto.builder()
                .dni("59021837")
                .name("Ana")
                .lastName("González")
                .birthDate("24-09-1998")
                .mail("ana.gonzalez@yahoo.com")
                .build();
    }
    public static PeopleDto getPeopleFive(){
        return PeopleDto.builder()
                .dni("123456")
                .name("pepeito")
                .lastName("Rodríguez")
                .birthDate("09-11-1982")
                .mail("miguel2@gmail.com")
                .build();
    }
    public static PeopleDto getPeopleSix(){
        return PeopleDto.builder()
                .dni("28374619")
                .name("Martina")
                .lastName("García")
                .birthDate("08-06-1995")
                .mail("martina_garcia@hotmail.com")
                .build();
    }



}
