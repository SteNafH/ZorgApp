package com.zorgapp;

import com.zorgapp.data.Data;
import com.zorgapp.languages.Language;
import com.zorgapp.languages.Languages;
import com.zorgapp.menus.LanguageMenu;
import com.zorgapp.menus.LoginMenu;
import com.zorgapp.menus.Menu;

public class Main {
    public static void main(String[] args) {
        Data.init("/data.json");

        System.out.println("\u001B[34mZorgApp\u001B[0m");

        LanguageMenu languageMenu = new LanguageMenu();
        languageMenu.show();

        Language language = languageMenu.getLanguage();
        Languages.init(language);
        System.out.println(Languages.getString("test"));

        LoginMenu loginMenu = new LoginMenu();
        loginMenu.show();

        Menu menu = loginMenu.getMenu();
        menu.show();
    }
}
