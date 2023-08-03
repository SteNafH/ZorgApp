package com.zorgapp.models;

public enum Medicine {
    DICLOFENAC("Diclofenac", "Diclofenac is an anti-inflammatory pain reliever. It has an analgesic, anti-inflammatory and fever-reducing effect."),
    AMOXICILLIN("Amoxicillin","Amoxicillin works against bacterial infections."),
    OMEPRAZOLE("Omeprazole", "Omeprazol is an antacid. It reduces the production of acid in the stomach."),
    DOXYCYCLINE("Doxycycline", "Doxycycline works against bacterial infections. Doxycycline also fights the malaria parasite."),
    IBUPROFEN("Ibuprofen", "Ibuprofen is an anti-inflammatory pain reliever. It has an analgesic, anti-inflammatory and fever-reducing effect.");

    private String name;
    private String info;

    Medicine(String name, String info) {
        this.name = name;
        this.info = info;
    }

    public String getName() {
        return name;
    }

    public String getInfo() {
        return info;
    }
}
