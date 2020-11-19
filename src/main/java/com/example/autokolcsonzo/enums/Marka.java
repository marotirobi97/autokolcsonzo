package com.example.autokolcsonzo.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Marka {
    BWM("BMW"),
    AUDI("Audi"),
    BENTLEY("Bentley"),
    BUGATTI("Bugatti"),
    HONDA("Honda"),
    PEUGEOT("Peugeot"),
    FORD("Ford"),
    FERRARI("Ferrari"),
    MAZDA("Mazda"),
    TESLA("Tesla");

    private final String marka;

    public String getMarka() {
        return marka;
    }
}
