package com.example.autokolcsonzo.service;

import com.example.autokolcsonzo.dto.FoglalasDto;
import com.example.autokolcsonzo.dto.SzabadAutoDto;
import com.example.autokolcsonzo.dto.VasarloDto;
import com.example.autokolcsonzo.entity.Auto;
import com.example.autokolcsonzo.entity.Foglalas;
import com.example.autokolcsonzo.entity.Vasarlo;
import com.example.autokolcsonzo.repository.AutoRepository;
import com.example.autokolcsonzo.repository.FoglalasRepository;
import com.example.autokolcsonzo.repository.VasarloRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@Data
@Service
public class VasarloService {

    @Autowired
    private AutoRepository autoRepository;

    @Autowired
    private VasarloRepository vasarloRepository;

    @Autowired
    private FoglalasRepository foglalasRepository;

    public List<SzabadAutoDto> getSzabadAutokKepekkel() {

        List<Auto> szabadAutok = autoRepository.findAllFreeCar();
        List<SzabadAutoDto> szabadAutoDto = new ArrayList<>();

        for (Auto auto : szabadAutok) {
            SzabadAutoDto szabadDto = new SzabadAutoDto();
            szabadDto.setAutoId(auto.getId());
            szabadDto.setMarka(auto.getMarka());
            szabadDto.setNapiAr(auto.getNapiAr());

            byte[] autoKep = auto.getKep();

            if(autoKep == null) {
                szabadDto.setDekodoltKep("Nincs kép");
            }else{
                String kep = Base64.getEncoder().encodeToString(autoKep);
                szabadDto.setDekodoltKep(kep);
            }
            szabadAutoDto.add(szabadDto);
        }

        return szabadAutoDto;
    }

    public String datumKezelo(FoglalasDto foglalasDto, RedirectAttributes redirectAttributes) {

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

        return null;
    }

    public void foglalasKezelo(VasarloDto vasarloDto, FoglalasDto foglalasDto, Auto lefoglaltAuto) {

        Vasarlo vasarlo = Vasarlo.builder().email(vasarloDto.getEmail()).cim(vasarloDto.getCim()).foglalandoNapokSzama(foglalasDto.getFoglalandoNapok()).telefonSzam(vasarloDto.getTelefonszam()).build();
        vasarloRepository.save(vasarlo);

        Foglalas foglalas = Foglalas.builder().auto(lefoglaltAuto).vasarlo(vasarlo).foglalandoNapokSzama(foglalasDto.getFoglalandoNapok()).foglalasOsszege(foglalasDto.getFoglalandoNapok() * lefoglaltAuto.getNapiAr()).build();
        foglalasRepository.save(foglalas);
    }
}
