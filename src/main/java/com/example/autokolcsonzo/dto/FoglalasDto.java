package com.example.autokolcsonzo.dto;

import com.example.autokolcsonzo.entity.Auto;
import lombok.Data;

import java.time.LocalDate;

@Data
public class FoglalasDto {

    private String kezdoDatum;
    private String zaroDatum;
    private Integer foglalandoNapok;
    private Integer autoId;
}
