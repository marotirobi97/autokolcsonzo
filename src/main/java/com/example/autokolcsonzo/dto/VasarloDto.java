package com.example.autokolcsonzo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VasarloDto {

    private String email;
    private String cim;
    private String telefonszam;
}