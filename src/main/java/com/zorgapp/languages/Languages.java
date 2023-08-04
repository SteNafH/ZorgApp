package com.zorgapp.languages;

import java.util.Locale;
import java.util.ResourceBundle;

public class Languages {
    private static ResourceBundle bundle;

    public static void init(Language language) {
        Languages.bundle = ResourceBundle.getBundle("messages", Locale.forLanguageTag(language.getLocale()));
    }

    public static String getString(String key) {
        return Languages.bundle.getString(key);
    }
}

