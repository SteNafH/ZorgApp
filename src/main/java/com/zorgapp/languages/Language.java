package com.zorgapp.languages;

public enum Language {
    ENGLISH("en"),
    DUTCH("nl");

    private final String locale;

    Language(String locale) {
        this.locale = locale;
    }

    public String getLocale() {
        return locale;
    }
}
