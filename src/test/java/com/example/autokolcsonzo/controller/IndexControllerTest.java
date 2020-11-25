package com.example.autokolcsonzo.controller;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

class IndexControllerTest {

    @Autowired
    private IndexController indexController;

//    @Test
//    void testMockMVC() throws Exception{
//        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(indexController).build();
//
//        mockMvc.perform(MockMvcRequestBuilders.get("/"))
//                .andExpect(status().isOk())
//                .andReturn().getModelAndView().getModel();
//    }
}