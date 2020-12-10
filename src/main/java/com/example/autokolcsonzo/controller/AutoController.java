package com.example.autokolcsonzo.controller;

import com.example.autokolcsonzo.entity.Auto;
import com.example.autokolcsonzo.repository.AutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AutoController {

    @Autowired
    private AutoRepository autoRepository;

    @GetMapping("/create")
    public String autoLetrehozasaGomb(Model model){
        model.addAttribute("auto", new Auto());

        return "admin/auto-letrehozasa";
    }

    @PostMapping("/create/car")
    public String autoLetrehozasa(@ModelAttribute("auto") Auto auto, RedirectAttributes redirectAttributes){
        autoRepository.save(auto);
        redirectAttributes.addFlashAttribute("message","Sikeresen létrehoztál egy új autót!");

        return "redirect:/admin/create";
    }

    @GetMapping("/list/auto")
    public String autokListázása(Model model){
        List<Auto> autoLista = autoRepository.findAll();
        model.addAttribute("autoLista",autoLista);

        return "admin/autok-listazasa";
    }

    @GetMapping("/edit/car/{carId}")
    public String autoSzerkesztes(@PathVariable("carId") Integer carId, Model model){
        Auto selectedCar = autoRepository.findAutoById(carId);
        model.addAttribute("auto", selectedCar);

        return "admin/autok-szerkesztese";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("auto") Auto auto){
        autoRepository.save(auto);

        return "redirect:/admin/list/auto";
    }

    @GetMapping("/list/rent")
    public String lefoglaltAutokLista(Model model){
        List<Auto> lefoglaltAutok = autoRepository.findAllRentedCar();
        model.addAttribute("rentedCar", lefoglaltAutok);

        return "admin/lefoglalt-autok";
    }
}
