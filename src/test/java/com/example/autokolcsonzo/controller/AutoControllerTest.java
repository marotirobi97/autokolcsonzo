package com.example.autokolcsonzo.controller;

import com.example.autokolcsonzo.entity.Auto;
import com.example.autokolcsonzo.enums.Aktivalt_e;
import com.example.autokolcsonzo.enums.Allapot;
import com.example.autokolcsonzo.repository.AutoRepository;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@WithMockUser(roles = "ADMIN")
public class AutoControllerTest {

    @Autowired
    private AutoController autoController;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AutoRepository autoRepository;

    @Before
    public void init() {

        InternalResourceViewResolver templateResolver = new InternalResourceViewResolver();
        templateResolver.setPrefix("/templates");
        templateResolver.setSuffix(".html");

        this.mockMvc = MockMvcBuilders.standaloneSetup(autoController).setViewResolvers(templateResolver).build();
    }

    @Test
    public void endPointCheckerCreate() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/admin/create"))
                .andExpect(status().isOk())
                .andExpect(view().name("admin/auto-letrehozasa"));
    }

    @Test
    public void create() throws Exception {

        MvcResult result = this.mockMvc
                .perform(get("/admin/create"))
                .andReturn();

        boolean auto = Objects.requireNonNull(result.getModelAndView()).getModel().containsKey("auto");
        assertThat(auto).isTrue();
    }

    @Test
    public void endPointCheckerCreateCar() throws Exception {

        MvcResult result = this.mockMvc
                .perform(post("/admin/create/car")
                        .requestAttr("auto", any(Auto.class)))
                .andReturn();

        mockMvc.perform(post("/admin/create/car"))      //redirect
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/admin/create"));
    }

    @Test
    public void createCarAndSave() throws Exception {

        Auto auto = new Auto(2,"AUDI",22,2200,Aktivalt_e.AKTIV.toString(),Allapot.SZABAD.toString(),null);

        Mockito.when(autoRepository.save(ArgumentMatchers.any())).thenReturn(new Auto());

        MvcResult result = this.mockMvc
                .perform(post("/admin/create/car")
                        .flashAttr("auto", auto))
                .andReturn();

        String message = (String)result.getFlashMap().get("message");
        assertThat(message).isNotNull();

    }

    @Test
    public void endPointCheckerListAuto() throws Exception {

        mockMvc.perform(get("/admin/list/auto"))
                .andExpect(status().isOk())
                .andExpect(view().name("admin/autok-listazasa"));
    }

    @Test
    public void listAuto() throws Exception {

        Auto auto = new Auto(2,"AUDI",22,2200,Aktivalt_e.AKTIV.toString(),Allapot.SZABAD.toString(),null);

        List<Auto> autoLista = new ArrayList<>();
        autoLista.add(auto);

        when(autoRepository.findAll()).thenReturn(autoLista);

        MvcResult result = mockMvc
                .perform(get("/admin/list/auto"))
                .andReturn();

        ModelAndView modelAndView = result.getModelAndView();
        List<Auto> autoList = (List<Auto>) modelAndView.getModel().get("autoLista");
        assertThat(autoList).isNotEmpty();
    }

    @Test
    public void endPointCheckerEditCar() throws Exception {

        Integer carId = 7;

        Auto auto = new Auto(carId,"AUDI",22,2200,Aktivalt_e.AKTIV.toString(),Allapot.SZABAD.toString(),null);

        when(autoRepository.findAutoById(carId)).thenReturn(auto);

        mockMvc.perform(get("/admin/edit/car/" + carId)
                    .requestAttr("carId",any(Integer.class)))
                .andExpect(status().isOk())
                .andExpect(view().name("admin/autok-szerkesztese"));
    }

    @Test
    public void editCar() throws Exception {
        Integer carId = 2;

        Auto auto = new Auto(carId,"AUDI",22,2200,Aktivalt_e.AKTIV.toString(),Allapot.SZABAD.toString(),null);

        when(autoRepository.findAutoById(carId)).thenReturn(auto);

        MvcResult result = mockMvc.perform(get("/admin/edit/car/" + carId)
                    .requestAttr("carId",carId))
                .andReturn();

        ModelAndView modelAndView = result.getModelAndView();
        Auto selectedCar = (Auto) modelAndView.getModel().get("auto");
        assertThat(selectedCar).isNotNull();
    }

    @Test
    public void endPointCheckerSave() throws Exception {

        MvcResult result = this.mockMvc
                .perform(get("/admin/save")
                        .requestAttr("auto", any(Auto.class)))
                .andReturn();

        mockMvc.perform(post("/admin/save"))      // redirect
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/admin/list/auto"));
    }

    @Test
    public void save() throws Exception {

        Auto auto = new Auto(2,"AUDI",22,2200,Aktivalt_e.AKTIV.toString(),Allapot.SZABAD.toString(),null);

        when(autoRepository.save(ArgumentMatchers.any(Auto.class))).thenReturn(new Auto());

        MvcResult result = this.mockMvc
                .perform(post("/admin/save")
                        .flashAttr("auto", auto))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/admin/list/auto"))
                .andReturn();
    }

    @Test
    public void endPointCheckerListRented() throws Exception {

        Auto auto = new Auto(7,"AUDI",22,2200,Aktivalt_e.AKTIV.toString(),Allapot.FOGLALT.toString(),null);
        List<Auto> autoList = new ArrayList<>();
        autoList.add(auto);

        when(autoRepository.findAllRentedCar()).thenReturn(autoList);

        mockMvc.perform(get("/admin/list/rent"))
                .andExpect(status().isOk())
                .andExpect(view().name("admin/lefoglalt-autok"));
    }

    @Test
    public void listRented() throws Exception {

        Auto auto = new Auto(2,"AUDI",22,2200,Aktivalt_e.AKTIV.toString(),Allapot.FOGLALT.toString(),null);

        List<Auto> autoLista = new ArrayList<>();
        autoLista.add(auto);
        when(autoRepository.findAllRentedCar()).thenReturn(autoLista);

        MvcResult result = mockMvc
                .perform(get("/admin/list/rent"))
                .andReturn();

        ModelAndView modelAndView = result.getModelAndView();
        List<Auto> autoList = (List<Auto>) modelAndView.getModel().get("rentedCar");
        assertThat(autoList).isNotEmpty();
    }
}