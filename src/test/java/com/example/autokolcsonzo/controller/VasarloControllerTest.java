package com.example.autokolcsonzo.controller;

import com.example.autokolcsonzo.dto.FoglalasDto;
import com.example.autokolcsonzo.dto.SzabadAutoDto;
import com.example.autokolcsonzo.dto.VasarloDto;
import com.example.autokolcsonzo.entity.Auto;
import com.example.autokolcsonzo.enums.Aktivalt_e;
import com.example.autokolcsonzo.enums.Allapot;
import com.example.autokolcsonzo.repository.AutoRepository;
import com.example.autokolcsonzo.repository.VasarloRepository;
import com.example.autokolcsonzo.service.VasarloService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class VasarloControllerTest {

    @Autowired
    private VasarloController vasarloController;

    @MockBean
    private AutoRepository autoRepository;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VasarloRepository vasarloRepository;

    @MockBean
    private VasarloService vasarloService;

    @Before
    public void init(){

        InternalResourceViewResolver templateResolver = new InternalResourceViewResolver();
        templateResolver.setPrefix("/templates");
        templateResolver.setSuffix(".html");

        this.mockMvc = MockMvcBuilders.standaloneSetup(vasarloController).setViewResolvers(templateResolver).build();
    }

    @Test
    public void listFreeCars() throws Exception {

        FoglalasDto foglalasDto = new FoglalasDto();

        doNothing().when(vasarloService).datumKezelo(any(FoglalasDto.class));

        byte[] img= {13,112,31,2};
        Auto auto = new Auto(2,"AUDI",22,2200, Aktivalt_e.AKTIV.toString(), Allapot.SZABAD.toString(),img);

        List<SzabadAutoDto> szabadAutoList = new ArrayList<>();
        SzabadAutoDto szabadAuto = new SzabadAutoDto();

        szabadAuto.builder().autoId(auto.getId()).napiAr(auto.getNapiAr()).marka(auto.getMarka()).dekodoltKep(Arrays.toString(auto.getKep())).build();
        szabadAutoList.add(szabadAuto);

        when(vasarloService.getSzabadAutokKepekkel()).thenReturn(szabadAutoList);

        MvcResult result = mockMvc.perform(post("/list/free/car")
                    .flashAttr("foglalasDto", foglalasDto))
                .andReturn();

        assertThat(szabadAutoList).isNotEmpty();
    }

    @Test
    public void reservation() throws Exception {

        Integer carId = 5;
        Integer foglalandoNapok = 5;

        FoglalasDto foglalasDto = new FoglalasDto();
        foglalasDto.setFoglalandoNapok(foglalandoNapok);

        Auto auto = new Auto(2,"AUDI",22,2200, Aktivalt_e.AKTIV.toString(), Allapot.SZABAD.toString(),null);

        when(autoRepository.findAutoById(carId)).thenReturn(auto);

        MvcResult result = mockMvc.perform(get("/reservation/" + carId)
                    .requestAttr("carId",carId)
                    .flashAttr("foglalasDto",foglalasDto))
                .andReturn();

        ModelAndView modelAndView = result.getModelAndView();

        Auto lefoglaltAuto = (Auto) modelAndView.getModel().get("auto");
        assertThat(lefoglaltAuto).isNotNull();

        Integer lefoglaltNapok = (Integer) modelAndView.getModel().get("foglalandoNapok");
        assertThat(lefoglaltNapok).isNotNull();

    }

    @Test
    public void customerRentIfAutoSzabad() throws Exception {

        Integer autoId = 5;

        VasarloDto vasarloDto = new VasarloDto();
        FoglalasDto foglalasDto = new FoglalasDto();

        foglalasDto.setAutoId(autoId);

        Auto szabadAuto = new Auto(2,"AUDI",22,2200, Aktivalt_e.AKTIV.toString(), Allapot.SZABAD.toString(),null);

        when(autoRepository.findAutoById(foglalasDto.getAutoId())).thenReturn(szabadAuto);

        MvcResult result = mockMvc.perform(post("/customer/rent")
                    .flashAttr("vasarloDto", vasarloDto)
                    .flashAttr("foglalasDto", foglalasDto))
                .andReturn();

        assertThat(szabadAuto.getAllapot()).isEqualTo(Allapot.FOGLALT.toString());
    }
    @Test
    public void customerRentIfAutoFoglalt() throws Exception {

        Integer autoId = 5;

        VasarloDto vasarloDto = new VasarloDto();
        FoglalasDto foglalasDto = new FoglalasDto();

        foglalasDto.setAutoId(autoId);

        Auto foglaltAuto = new Auto(2,"AUDI",22,2200, Aktivalt_e.AKTIV.toString(), Allapot.FOGLALT.toString(),null);

        when(autoRepository.findAutoById(foglalasDto.getAutoId())).thenReturn(foglaltAuto);

        MvcResult result = mockMvc.perform(post("/customer/rent")
                    .flashAttr("vasarloDto", vasarloDto)
                    .flashAttr("foglalasDto", foglalasDto))
                    .andExpect(status().isOk())
                    .andExpect(view().name("uzenetKezelo/marLefoglaltAuto"))
                    .andReturn();
    }
}
