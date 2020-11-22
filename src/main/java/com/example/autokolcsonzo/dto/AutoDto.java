package com.example.autokolcsonzo.dto;

import com.example.autokolcsonzo.enums.Aktivalt_e;
import lombok.Data;

@Data
public class AutoDto {

    private String marka;
    private Integer hanyEves;
    private Integer napiAr;
    private Aktivalt_e aktivalt_e = Aktivalt_e.AKTIV;


    private byte[] image;
}
