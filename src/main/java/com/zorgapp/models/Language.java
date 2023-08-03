package com.zorgapp.models;

public enum Language {
    ENGLISH("en"),
    DUTCH("nl");

    private String locale;

    Language(String locale) {
        this.locale = locale;
    }

    public String getLocale() {
        return locale;
    }
}
