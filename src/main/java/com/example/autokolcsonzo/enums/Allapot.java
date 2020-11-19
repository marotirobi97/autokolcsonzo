package com.example.autokolcsonzo.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Allapot {
    FOGLALT("Foglalt"),
    SZABAD("Szabad");

    private final String allapot;

    public String getAllapot(){
        return allapot;
    }
}
