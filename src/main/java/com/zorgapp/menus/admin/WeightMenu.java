package com.zorgapp.menus.admin;

import com.zorgapp.menus.Menu;
import com.zorgapp.models.Language;
import com.zorgapp.models.Patient;
import com.zorgapp.models.Weight;

import java.util.Scanner;

public class WeightMenu implements Menu {
    private final Language language;
    private final Patient patient;

    public WeightMenu(Language language, Patient patient) {
        this.language = language;
        this.patient = patient;
    }

    @Override
    public void show() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            StringBuilder builder = new StringBuilder();
            builder.append("\r\n-----------------------------------------------");

            for (Weight weight : this.patient.getWeightList()) {
                builder.append("\r\n")
                        .append(weight.getDate())
                        .append(",\t")
                        .append(weight.getWeight())
                        .append("KG: \t");

                for (int i = 0; i < weight.getWeight() / 2; i++) {
                    builder.append("*");
                }
            }

            builder.append("\r\n-----------------------------------------------")
                    .append("\r\n0 - RETURN")
                    .append("\r\n1 - EDIT PREVIOUS WEIGHT")
                    .append("\r\n2 - ADD NEW WEIGHT")
                    .append("\r\n3 - DELETE WEIGHT");
            System.out.println(builder);

            String input = scanner.nextLine();

            //TODO
            switch (input) {
                case "0" -> {
                    return;
                }
                case "1" -> {

                }
                case "2" -> {

                }
                case "3" -> {

                }
                default -> System.err.println("\r\nINVALID INPUT");
            }
        }
    }
}
