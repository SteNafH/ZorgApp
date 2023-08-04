package com.zorgapp.menus.admin;

import com.zorgapp.data.Data;
import com.zorgapp.menus.Menu;
import com.zorgapp.models.Language;
import com.zorgapp.models.Patient;
import com.zorgapp.models.Weight;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class WeightMenu implements Menu {
    private final Data data;
    private final Language language;
    private final Patient patient;

    public WeightMenu(Language language, Patient patient) {
        this.data = Data.getInstance();
        this.language = language;
        this.patient = patient;
    }

    @Override
    public void show() {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Weight> weightList = this.patient.getWeightList();

        while (true) {
            StringBuilder builder = new StringBuilder();
            builder.append("\r\n-----------------------------------------------");

            for (Weight weight : weightList) {
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
                    Weight weight = this.selectWeightMenu(weightList, "\r\nCHOOSE WEIGHT TO EDIT");

                    if (weight == null) {
                        continue;
                    }

                    System.out.println("\r\nENTER WEIGHT (KG):");
                    double kg = Double.parseDouble(scanner.nextLine());

                    System.out.println("\r\nENTER DATE OF MEASURE (YYYY-MM-DD):");
                    LocalDate dateOfMeasure = LocalDate.parse(scanner.nextLine());

                    weight.setWeight(kg);
                    weight.setDate(dateOfMeasure);
                    weightList.sort((patient1, patient2) -> patient2.getDate().compareTo(patient1.getDate()));
                }
                case "2" -> {
                    System.out.println("\r\nENTER WEIGHT (KG):");
                    double weight = Double.parseDouble(scanner.nextLine());

                    this.patient.addWeight(weight);
                }
                case "3" -> {
                    Weight weight = this.selectWeightMenu(weightList, "\r\nCHOOSE WEIGHT TO DELETE");

                    if (weight != null) {
                        weightList.remove(weight);
                    }
                }
                default -> {
                    System.err.println("\r\nINVALID INPUT");
                    continue;
                }
            }

            this.data.updatePatient(patient);
        }
    }

    private Weight selectWeightMenu(ArrayList<Weight> weightList, String chooseText) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            StringBuilder builder = new StringBuilder();
            builder.append("\r\n-----------------------------------------------")
                    .append("\r\n0 - RETURN");

            for (int i = 0; i < weightList.size(); i++) {
                Weight weight = weightList.get(i);
                builder.append("\r\n").append(i + 1).append(" - ").append(weight.getDate()).append(", ").append(weight.getWeight()).append(" KG");
            }

            builder.append("\r\n-----------------------------------------------")
                    .append(chooseText);
            System.out.println(builder);

            String input = scanner.nextLine();

            if (input.equals("0")) {
                return null;
            }

            try {
                int index = Integer.parseInt(input);
                Weight weight = weightList.get(index - 1);

                if (weight != null) {
                    return weight;
                }

                System.err.println("\r\nINVALID INPUT");
            } catch (NumberFormatException exception) {
                System.err.println("\r\nINVALID INPUT");
            }
        }
    }
}
