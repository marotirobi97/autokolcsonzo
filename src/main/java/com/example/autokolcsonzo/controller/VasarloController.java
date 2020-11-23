package com.example.autokolcsonzo.controller;

import com.example.autokolcsonzo.dto.FoglalasDto;
import com.example.autokolcsonzo.dto.SzabadAutoDto;
import com.example.autokolcsonzo.dto.VasarloDto;
import com.example.autokolcsonzo.entity.Auto;
import com.example.autokolcsonzo.enums.Allapot;
import com.example.autokolcsonzo.repository.AutoRepository;
import com.example.autokolcsonzo.repository.FoglalasRepository;
import com.example.autokolcsonzo.repository.VasarloRepository;
import com.example.autokolcsonzo.service.VasarloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping
@SessionAttributes("foglalasDto")
public class VasarloController {

    @SuppressWarnings("unused")
    @ModelAttribute
    public FoglalasDto foglalasDto(){
        return new FoglalasDto();
    }

    @SuppressWarnings("unused")
    @Autowired
    private AutoRepository autoRepository;

    @SuppressWarnings("unused")
    @Autowired
    private VasarloService vasarloService;

    @SuppressWarnings("unused")
    @Autowired
    private VasarloRepository vasarloRepository;

    @SuppressWarnings("unused")
    @Autowired
    private FoglalasRepository foglalasRepository;

    @PostMapping("/list/free/car")
    public String szabadKocsikListazasa(@ModelAttribute FoglalasDto foglalasDto, Model model, RedirectAttributes redirectAttributes) throws IOException {

        String datumKezelo = vasarloService.datumKezelo(foglalasDto, redirectAttributes);
        if (datumKezelo != null) return datumKezelo;

        List<SzabadAutoDto> szabadAutoDto = vasarloService.getSzabadAutokKepekkel();

        redirectAttributes.addFlashAttribute("autoLista",szabadAutoDto);

        return "redirect:/index";
    }

    @GetMapping("/reservation/{carId}")
    public String foglalasOldal(@PathVariable("carId") Integer carId,Model model,@ModelAttribute FoglalasDto foglalasDto){

        Auto lefoglaltAuto = autoRepository.findAutoById(carId);
        foglalasDto.setAutoId(carId);

        model.addAttribute("auto",lefoglaltAuto);
        model.addAttribute("foglalandoNapok",foglalasDto.getFoglalandoNapok());
        model.addAttribute("vasarloDto",new VasarloDto());

        return "public/foglalas";
    }

    @PostMapping("/customer/rent")
    public String vasarloFoglal(Model model, @ModelAttribute("vasarloDto") VasarloDto vasarloDto,@ModelAttribute FoglalasDto foglalasDto,RedirectAttributes redirectAttributes){

        Auto lefoglaltAuto = autoRepository.findAutoById(foglalasDto.getAutoId());

        if(lefoglaltAuto.getAllapot().equals("FOGLALT")){
            return "uzenetKezelo/marLefoglaltAuto";
        }

        lefoglaltAuto.setAllapot(Allapot.FOGLALT.toString());

        vasarloService.foglalasKezelo(vasarloDto, foglalasDto, lefoglaltAuto);

        return "redirect:/index";
    }
}
