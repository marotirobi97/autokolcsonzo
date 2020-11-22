package com.example.autokolcsonzo.controller;

import com.example.autokolcsonzo.dto.FoglalasDto;
import com.example.autokolcsonzo.dto.VasarloDto;
import com.example.autokolcsonzo.entity.Auto;
import com.example.autokolcsonzo.entity.Foglalas;
import com.example.autokolcsonzo.entity.Vasarlo;
import com.example.autokolcsonzo.enums.Allapot;
import com.example.autokolcsonzo.repository.AutoRepository;
import com.example.autokolcsonzo.repository.FoglalasRepository;
import com.example.autokolcsonzo.repository.VasarloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.swing.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping
@SessionAttributes("foglalasDto")
public class VasarloController {

    @ModelAttribute
    public FoglalasDto foglalasDto(){
        return new FoglalasDto();
    }

    @Autowired
    private AutoRepository autoRepository;

    @Autowired
    private VasarloRepository vasarloRepository;

    @Autowired
    private FoglalasRepository foglalasRepository;

    @PostMapping("/list/free/car")
    public String szabadKocsikListazasa(@ModelAttribute FoglalasDto foglalasDto, Model model, RedirectAttributes redirectAttributes){
        List<Auto> szabadAutok = autoRepository.findAllFreeCar();

        SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
        String kezdo = foglalasDto.getKezdoDatum();
        String zaro = foglalasDto.getZaroDatum();
        try{
            Date dateBefore = myFormat.parse(kezdo);
            Date dateAfter = myFormat.parse(zaro);
            Date todayDay = myFormat.parse(LocalDate.now().toString());
            if(dateBefore.before(todayDay) || dateAfter.before(todayDay) || dateAfter.before(dateBefore)){
                redirectAttributes.addFlashAttribute("message","Nem jó dátumokat adtál meg!");
                return "redirect:/index";
            }
            long kulonbseg = dateAfter.getTime() - dateBefore.getTime();
            int daysBetween = (int)(kulonbseg / (1000*60*60*24));
            foglalasDto.setFoglalandoNapok(daysBetween);
        } catch (Exception e) {
            e.printStackTrace();
        }
//        byte[] byteArray=null; //need to initialize it
//        ImageIcon imageIcon = new ImageIcon(byteArray);
//        imageIcon.getImage();

        redirectAttributes.addFlashAttribute("autoLista",szabadAutok);

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
    public String vasarloFoglal(Model model, @ModelAttribute("vasarloDto") VasarloDto vasarloDto,@ModelAttribute FoglalasDto foglalasDto){
        Auto lefoglaltAuto = autoRepository.findAutoById(foglalasDto.getAutoId());
        lefoglaltAuto.setAllapot(Allapot.FOGLALT.toString());

        Vasarlo vasarlo = Vasarlo.builder().email(vasarloDto.getEmail()).cim(vasarloDto.getCim()).foglalandoNapokSzama(foglalasDto.getFoglalandoNapok()).telefonSzam(vasarloDto.getTelefonszam()).build();
        vasarloRepository.save(vasarlo);

        Foglalas foglalas = Foglalas.builder().auto(lefoglaltAuto).vasarlo(vasarlo).foglalandoNapokSzama(foglalasDto.getFoglalandoNapok()).foglalasOsszege(foglalasDto.getFoglalandoNapok() * lefoglaltAuto.getNapiAr()).build();
        foglalasRepository.save(foglalas);

        return "redirect:/index";
    }
}
