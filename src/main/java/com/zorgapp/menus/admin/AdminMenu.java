package com.zorgapp.menus.admin;

import com.zorgapp.data.Data;
import com.zorgapp.menus.Menu;
import com.zorgapp.models.Language;
import com.zorgapp.models.Patient;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class AdminMenu implements Menu {
    private final Data data;
    private final Language language;
    private String sortOption;

    public AdminMenu(Language language) {
        this.data = Data.getInstance();
        this.language = language;
        this.sortOption = "SURNAME";
    }

    @Override
    public void show() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            ArrayList<Patient> patients = this.data.getPatients();
            if (this.sortOption.equals("SURNAME")) {
                patients.sort(Comparator.comparing(Patient::getSurName));
            } else {
                patients.sort(Comparator.comparing(Patient::getId));
            }

            StringBuilder builder = new StringBuilder();
            builder.append("\r\n-----------------------------------------------");

            for (Patient patient : patients) {
                builder.append("\r\n").append(patient);
            }

            builder.append("\r\n-----------------------------------------------")
                    .append("\r\n0 - STOP PROGRAM")
                    .append("\r\n1 - CHOOSE PATIENT")
                    .append("\r\n2 - ADD PATIENT")
                    .append("\r\n3 - SORT BY ").append(this.sortOption)
                    .append("\r\n\r\nENTER CHOICE:");

            System.out.println(builder);
            String input = scanner.nextLine();

            switch (input) {
                case "0" -> System.exit(0);
                case "1" -> new ChoosePatientMenu(this.language).show();
                case "2" -> new AddPatientMenu(this.language).show();
                case "3" -> this.sortOption = this.sortOption.equals("SURNAME") ? "PATIENT NUMBER" : "SURNAME";
                default -> System.err.println("\r\nINVALID INPUT");
            }
        }
    }
}
