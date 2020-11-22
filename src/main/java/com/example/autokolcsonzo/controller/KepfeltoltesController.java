package com.example.autokolcsonzo.controller;

import com.example.autokolcsonzo.dto.KepDto;
import com.example.autokolcsonzo.entity.Auto;
import com.example.autokolcsonzo.repository.AutoRepository;
import com.example.autokolcsonzo.repository.KepRepository;
import com.example.autokolcsonzo.service.IsTarhelyService;
import com.example.autokolcsonzo.service.TarthelyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;


@Controller
@SessionAttributes("kepDto")
public class KepfeltoltesController {

    @ModelAttribute
    public KepDto kepDto(){
        return new KepDto();
    }

    @Autowired
    private KepRepository kepRepository;

    @Autowired
    private TarthelyService tarhelyService;

    @Autowired
    private AutoRepository autoRepository;

    @GetMapping("/admin/upload/{carId}")
    public String upload(@PathVariable("carId") Integer autoId, @ModelAttribute KepDto kepDto){
        kepDto.setAutoId(autoId);
        return "admin/kep-feltoltes";
    }

    @PostMapping("/upload/image")
    public String kepfeltoltes(@RequestParam("imageFile") MultipartFile imageFile,@ModelAttribute KepDto kepDto, RedirectAttributes redirectAttributes) throws Exception {

        Integer autoId = kepDto.getAutoId();
        tarhelyService.saveImage(imageFile,autoId);
        redirectAttributes.addFlashAttribute("message", "Sikeresen feltöltöttél egy képet!");

        return "redirect:/admin/upload/" + autoId;
    }
}