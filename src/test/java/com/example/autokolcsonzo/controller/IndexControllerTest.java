package com.example.autokolcsonzo.controller;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
class IndexControllerTest {

    private static final String BASE_URL = "/";

    @Autowired
    private IndexController indexController;

    @Autowired
    private MockMvc mockMvc;

    @Before
    public void init() {
        InternalResourceViewResolver templateResolver = new InternalResourceViewResolver();
        templateResolver.setPrefix("/templates");
        templateResolver.setSuffix(".html");

        this.mockMvc = MockMvcBuilders.standaloneSetup(indexController).setViewResolvers(templateResolver).build();
    }

    @Test
    void testMockMVC() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get(BASE_URL))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/index"));
    }
}