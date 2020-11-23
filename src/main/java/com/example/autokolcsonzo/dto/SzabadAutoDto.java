package com.example.autokolcsonzo.dto;

import com.example.autokolcsonzo.enums.Marka;
import lombok.Data;
import org.yaml.snakeyaml.error.Mark;

@Data
public class SzabadAutoDto {

    private Integer autoId;
    private String marka;
    private Integer napiAr;
    private String dekodoltKep;

}
