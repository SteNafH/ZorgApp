package com.zorgapp.models;

import com.zorgapp.languages.Languages;

public enum Medicine {
    DICLOFENAC("diclofenac"),
    AMOXICILLIN("amoxicillin"),
    OMEPRAZOLE("omeprazole"),
    DOXYCYCLINE("doxycycline"),
    IBUPROFEN("ibuprofen");

    private final String key;

    Medicine(String key) {
        this.key = key;
    }

    public String getName() {
        return Languages.getString(this.key);
    }

    public String getInfo() {
        return Languages.getString(this.key + "Info");
    }
}
