package com.example.autokolcsonzo.controller;

import com.example.autokolcsonzo.dto.FoglalasDto;
import com.example.autokolcsonzo.entity.Auto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;

@Controller
@RequestMapping
public class IndexController {

    @GetMapping("/")
    public String indexInit(){
        return "redirect:/index";
    }

    @GetMapping("/index")
    public String index(Model model, @ModelAttribute("autoLista") ArrayList<Auto> autoLista, @ModelAttribute("kepekLista")ArrayList<Image> kepek){
        model.addAttribute("foglalasDto", new FoglalasDto());
        model.addAttribute("maiDatum", LocalDate.now());

        return "index";
    }

    @GetMapping("/login")
    public String login(){
        return "redirect:/login";
    }

    @GetMapping("/admin/adminPage")
    public String adminPage(){
        return "admin/admin-page";
    }
}
