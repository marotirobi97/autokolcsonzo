package com.example.autokolcsonzo.controller;

import com.example.autokolcsonzo.service.IsTarhelyService;
import com.example.autokolcsonzo.service.TarthelyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class KepfeltoltesController {

    @Autowired
    private TarthelyService tarhelyService;

    @GetMapping("/upload")
    public String upload(){
        return "admin/kep-feltoltes";
    }

    @PostMapping("/upload/image")
    public String kepfeltoltes(@RequestParam("imageFile") MultipartFile imageFile, RedirectAttributes redirectAttributes) throws Exception {
        tarhelyService.saveImage(imageFile,7);
        redirectAttributes.addFlashAttribute("message", "Sikeresen feltöltöttél egy képet!");

        return "redirect:/upload";
    }
}