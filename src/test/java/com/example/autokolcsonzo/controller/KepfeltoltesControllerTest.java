package com.example.autokolcsonzo.controller;

import com.example.autokolcsonzo.dto.KepDto;
import com.example.autokolcsonzo.service.TarhelyService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.io.FileInputStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@WithMockUser(roles = "ADMIN")
public class KepfeltoltesControllerTest {

    @Autowired
    private KepfeltoltesController kepfeltoltesController;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TarhelyService tarhelyService;

    @Before
    public void init(){

        InternalResourceViewResolver templateResolver = new InternalResourceViewResolver();
        templateResolver.setPrefix("/templates");
        templateResolver.setSuffix(".html");

        this.mockMvc = MockMvcBuilders.standaloneSetup(kepfeltoltesController).setViewResolvers(templateResolver).build();
    }

    @Test
    public void uploadEndPointChecker() throws Exception {
        KepDto kepDto = new KepDto();
        Integer carId = 5;

        String URL = "/admin/upload/" + carId;

        mockMvc.perform(get(URL)
                .requestAttr("carId",carId)
                .flashAttr("kepDto",kepDto))
                .andExpect(status().isOk())
                .andExpect(view().name("admin/kep-feltoltes"))
                .andReturn();

        assertThat(kepDto.getAutoId()).isNotNull();
    }

    @Test
    public void uploadImage() throws Exception {
        Integer carId = 5;

        KepDto kepDto = new KepDto();
        kepDto.setAutoId(carId);

        FileInputStream fis = new FileInputStream("/home/robi/dev/autokolcsonzo/src/main/resources/static/img/auto.jpg");
        MockMultipartFile imageFile = new MockMultipartFile("file", fis);

        doNothing().when(tarhelyService).saveImage(any(MultipartFile.class),any(Integer.class));

        MvcResult result = mockMvc.perform(multipart("/admin/upload/image")
                        .file("imageFile", imageFile.getBytes())
                        .flashAttr("kepDto", kepDto))
                .andReturn();

        String flashAttr = (String) result.getFlashMap().get("message");
        assertThat(flashAttr).isNotNull();
    }

    @Test
    public void uploadImage_ImageIsNull() throws Exception {

        Integer carId = 5;

        KepDto kepDto = new KepDto();
        kepDto.setAutoId(carId);

        MvcResult result = mockMvc.perform(multipart("/admin/upload/image")
                        .file("imageFile", null)
                        .flashAttr("kepDto", kepDto))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/admin/upload/" + carId))
                .andReturn();
    }
}
