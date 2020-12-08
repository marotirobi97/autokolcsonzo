package com.example.autokolcsonzo.dto;

import com.example.autokolcsonzo.enums.Marka;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.yaml.snakeyaml.error.Mark;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class SzabadAutoDto {

    private Integer autoId;
    private String marka;
    private Integer napiAr;
    private String dekodoltKep;

}
