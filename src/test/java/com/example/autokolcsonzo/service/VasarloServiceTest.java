package com.example.autokolcsonzo.service;

import com.example.autokolcsonzo.dto.FoglalasDto;
import com.example.autokolcsonzo.dto.SzabadAutoDto;
import com.example.autokolcsonzo.dto.VasarloDto;
import com.example.autokolcsonzo.entity.Auto;
import com.example.autokolcsonzo.enums.Aktivalt_e;
import com.example.autokolcsonzo.enums.Allapot;
import com.example.autokolcsonzo.repository.AutoRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class VasarloServiceTest {

    @Autowired
    private VasarloService vasarloService;

    @MockBean
    private AutoRepository autoRepository;

    @Test(expected = Exception.class)
    public void datumKezelo_KezdoDatumIsNull() throws Exception {

        FoglalasDto foglalasDto = new FoglalasDto();

        vasarloService.datumKezelo(foglalasDto);
    }

    @Test(expected = Exception.class)
    public void datumKezelo_ZaroDatumIsNull() throws Exception {

        FoglalasDto foglalasDto = new FoglalasDto();
        foglalasDto.setKezdoDatum("2020-11-20");
        vasarloService.datumKezelo(foglalasDto);
    }

    @Test
    public void getSzabadAutokKepekkel() throws Exception {

        byte[] img= {13,112,31,2};

        Auto auto = new Auto(2,"AUDI",22,2200, Aktivalt_e.AKTIV.toString(), Allapot.SZABAD.toString(),img);

        List<Auto> autoLista = new ArrayList<>();
        autoLista.add(auto);
        when(autoRepository.findAllFreeCar()).thenReturn(autoLista);

        List<SzabadAutoDto> szabadAutoLista = vasarloService.getSzabadAutokKepekkel();
        assertThat(szabadAutoLista).isNotEmpty();
    }

    @Test
    public void foglalasKezelo() throws Exception {

        VasarloDto vasarloDto = new VasarloDto();
        vasarloDto.builder().email("valamilyenemailcim@citromail.hu").cim("Rakott krumpli utca 22").telefonszam("0634234256").build();

        FoglalasDto foglalasDto = new FoglalasDto();
        foglalasDto.setFoglalandoNapok(5);

        Auto auto = new Auto(2,"AUDI",22,2200, Aktivalt_e.AKTIV.toString(), Allapot.SZABAD.toString(),null);

        vasarloService.foglalasKezelo(vasarloDto,foglalasDto,auto);
    }

//    @Test
//    public void isAutoFoglalt() throws Exception {
//
//        VasarloDto vasarloDto = new VasarloDto();
//        vasarloDto.builder().email("valamilyenemailcim@citromail.hu").cim("Rakott krumpli utca 22").telefonszam("0634234256").build();
//
//        FoglalasDto foglalasDto = new FoglalasDto();
//        foglalasDto.setFoglalandoNapok(5);
//
//        Auto auto = new Auto(2,"AUDI",22,2200, Aktivalt_e.AKTIV.toString(), Allapot.FOGLALT.toString(),null);
//
//
//
//    }
}