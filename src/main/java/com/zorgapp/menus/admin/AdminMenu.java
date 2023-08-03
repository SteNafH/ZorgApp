package com.zorgapp.menus.admin;

import com.zorgapp.data.Data;
import com.zorgapp.menus.Menu;
import com.zorgapp.models.Language;
import com.zorgapp.models.Patient;

import java.util.Scanner;

public class AdminMenu implements Menu {
    private final Data data;
    private final String sortOption;
    private final Language language;

    public AdminMenu(Language language) {
        this.data = Data.getInstance();
        this.sortOption = "SURNAME";
        this.language = language;
    }

    @Override
    public void show() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            StringBuilder builder = new StringBuilder();
            builder.append("\r\n-----------------------------------------------");

            for (Patient patient : this.data.getPatients()) {
                builder.append("\r\n").append(patient);
            }

            builder.append("\r\n-----------------------------------------------")
                    .append("\r\n0 - STOP PROGRAM")
                    .append("\r\n1 - CHOOSE PATIENT")
                    .append("\r\n2 - ADD PATIENT")
                    .append("\r\n3 - DELETE PATIENT")
                    .append("\r\n4 - SORT BY ").append(sortOption)
                    .append("\r\n\r\nENTER CHOICE:");

            System.out.println(builder);
            String input = scanner.nextLine();

            switch (input) {
                case "0" -> System.exit(0);
                case "1" -> {

                }
                case "2" -> {

                }
                case "3" -> {

                }
                case "4" -> {

                }
                default -> System.err.println("\r\nINVALID INPUT");
            }
        }
    }
}
