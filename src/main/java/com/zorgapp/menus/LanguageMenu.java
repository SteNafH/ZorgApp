package com.zorgapp.menus;

import com.zorgapp.languages.Language;

import java.util.Scanner;

public class LanguageMenu implements Menu {
    private Language language;

    public void show() {
        Scanner scanner = new Scanner(System.in);

        StringBuilder builder = new StringBuilder();
        builder.append("\r\nCHOOSE YOUR PREFERRED LANGUAGE // KIES UW VOORKEURSTAAL")
                .append("\r\n0 - STOP PROGRAM // STOP HET PROGRAMMA")
                .append("\r\n1 - DUTCH // NEDERLANDS")
                .append("\r\n2 - ENGLISH // ENGELS");

        while (true) {
            System.out.println(builder);
            String language = scanner.nextLine();

            switch (language) {
                case "0" -> System.exit(0);
                case "1" -> {
                    this.language = Language.DUTCH;
                    return;
                }
                case "2" -> {
                    this.language = Language.ENGLISH;
                    return;
                }
                default -> System.err.println("\r\nINVALID INPUT");
            }
        }
    }

    public Language getLanguage() {
        return this.language;
    }
}
