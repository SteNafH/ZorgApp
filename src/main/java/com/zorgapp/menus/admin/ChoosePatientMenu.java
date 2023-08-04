package com.zorgapp.menus.admin;

import com.zorgapp.data.Data;
import com.zorgapp.exceptions.PatientNotFoundException;
import com.zorgapp.languages.Languages;
import com.zorgapp.menus.Menu;
import com.zorgapp.models.Patient;

import java.util.Scanner;

public class ChoosePatientMenu implements Menu {
    @Override
    public void show() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            StringBuilder builder = new StringBuilder();
            builder.append("\r\n-----------------------------------------------");

            for (Patient patient : Data.getPatients()) {
                builder.append("\r\n").append(patient);
            }

            builder.append("\r\n-----------------------------------------------")
                    .append("\r\n").append(Languages.getString("enterPatientNumber"));

            System.out.println(builder);
            String input = scanner.nextLine();

            if (input.equals("0")) {
                return;
            }

            try {
                Patient patient = Data.getPatient(Integer.parseInt(input));
                new PatientMenu(patient).show();
            } catch (PatientNotFoundException | NumberFormatException exception) {
                System.err.println("\r\n" + Languages.getString("invalidEntry"));
            }
        }
    }
}
