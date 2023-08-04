package com.zorgapp.menus.admin;

import com.zorgapp.data.Data;
import com.zorgapp.languages.Languages;
import com.zorgapp.menus.Menu;
import com.zorgapp.models.Patient;
import com.zorgapp.models.Weight;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class WeightMenu implements Menu {
    private final Patient patient;

    public WeightMenu(Patient patient) {
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
                    .append("\r\n0 - ").append(Languages.getString("return"))
                    .append("\r\n1 - ").append(Languages.getString("editPreviousWeight"))
                    .append("\r\n2 - ").append(Languages.getString("addNewWeight"))
                    .append("\r\n3 - ").append(Languages.getString("deleteWeight"));
            System.out.println(builder);

            String input = scanner.nextLine();

            switch (input) {
                case "0" -> {
                    return;
                }
                case "1" -> {
                    Weight weight = this.selectWeightMenu(weightList, "\r\n" + Languages.getString("chooseWeightToEdit"));

                    if (weight == null) {
                        continue;
                    }

                    System.out.println("\r\n" + Languages.getString("enterWeight") + " (KG):");
                    double kg = Double.parseDouble(scanner.nextLine());

                    System.out.println("\r\n" + Languages.getString("enterDateOfMeasure") + "(YYYY-MM-DD):");
                    LocalDate dateOfMeasure = LocalDate.parse(scanner.nextLine());

                    weight.setWeight(kg);
                    weight.setDate(dateOfMeasure);
                    weightList.sort((patient1, patient2) -> patient2.getDate().compareTo(patient1.getDate()));
                }
                case "2" -> {
                    System.out.println("\r\n" + Languages.getString("enterWeight") + " (KG):");
                    double weight = Double.parseDouble(scanner.nextLine());

                    this.patient.addWeight(weight);
                }
                case "3" -> {
                    Weight weight = this.selectWeightMenu(weightList, "\r\n" + Languages.getString("chooseWeightToDelete"));

                    if (weight != null) {
                        weightList.remove(weight);
                    }
                }
                default -> {
                    System.err.println("\r\n" + Languages.getString("invalidInput"));
                    continue;
                }
            }

            Data.updatePatient();
        }
    }

    private Weight selectWeightMenu(ArrayList<Weight> weightList, String chooseText) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            StringBuilder builder = new StringBuilder();
            builder.append("\r\n-----------------------------------------------")
                    .append("\r\n0 - ").append(Languages.getString("return"));

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

                System.err.println("\r\n" + Languages.getString("invalidInput"));
            } catch (NumberFormatException exception) {
                System.err.println("\r\n" + Languages.getString("invalidInput"));
            }
        }
    }
}
