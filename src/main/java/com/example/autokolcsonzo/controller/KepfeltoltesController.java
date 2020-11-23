package com.example.autokolcsonzo.controller;

import com.example.autokolcsonzo.dto.KepDto;
import com.example.autokolcsonzo.repository.AutoRepository;
import com.example.autokolcsonzo.service.TarhelyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@SessionAttributes("kepDto")
public class KepfeltoltesController {

    @ModelAttribute
    public KepDto kepDto(){
        return new KepDto();
    }

    @Autowired
    private TarhelyService tarhelyService;

    @Autowired
    private AutoRepository autoRepository;

    @GetMapping("/admin/upload/{carId}")
    public String upload(@PathVariable("carId") Integer autoId, @ModelAttribute KepDto kepDto){
        kepDto.setAutoId(autoId);

        return "admin/kep-feltoltes";
    }

    @PostMapping("/admin/upload/image")
    public String kepfeltoltes(@RequestParam("imageFile") MultipartFile imageFile,@ModelAttribute KepDto kepDto, RedirectAttributes redirectAttributes) throws Exception {
        Integer autoId = kepDto.getAutoId();

        if(imageFile.isEmpty()){
            redirectAttributes.addFlashAttribute("noImageUploaded", "Nem választottál ki képet.");
            return "redirect:/admin/upload/" + autoId;
        }

        tarhelyService.saveImage(imageFile,autoId);
        redirectAttributes.addFlashAttribute("message", "Sikeresen feltöltöttél egy képet!");

        return "redirect:/admin/upload/" + autoId;
    }
}