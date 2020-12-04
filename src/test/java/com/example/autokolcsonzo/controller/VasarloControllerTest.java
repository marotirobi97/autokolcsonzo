package com.example.autokolcsonzo.controller;

import com.example.autokolcsonzo.dto.FoglalasDto;
import com.example.autokolcsonzo.repository.VasarloRepository;
import com.example.autokolcsonzo.service.VasarloServiceTest;
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
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class VasarloControllerTest {

    @Autowired
    private VasarloController vasarloController;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VasarloRepository vasarloRepository;

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

        MvcResult result = mockMvc.perform(post("/list/free/car")
                .flashAttr("foglalasDto", foglalasDto))
                .andReturn();

        VasarloServiceTest vasarloServiceTest = new VasarloServiceTest();
        vasarloServiceTest.datumKezelo_KezdoDatumIsNull();
        vasarloServiceTest.datumKezelo_KezdoDatumIsNull();
        vasarloServiceTest.getSzabadAutokKepekkel();


    }
}
