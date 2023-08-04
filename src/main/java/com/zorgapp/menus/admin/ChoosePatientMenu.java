package com.zorgapp.menus.admin;

import com.zorgapp.data.Data;
import com.zorgapp.exceptions.PatientNotFoundException;
import com.zorgapp.menus.Menu;
import com.zorgapp.models.Language;
import com.zorgapp.models.Patient;

import java.util.Scanner;

public class ChoosePatientMenu implements Menu {
    private final Data data;
    private final Language language;

    public ChoosePatientMenu(Language language) {
        this.data = Data.getInstance();
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
                    .append("\r\n\r\nENTER PATIENT NUMBER: (0 = Return)");

            System.out.println(builder);
            String input = scanner.nextLine();

            if (input.equals("0")) {
                return;
            }

            try {
                Patient patient = this.data.getPatient(Integer.parseInt(input));
                new PatientMenu(this.language, patient).show();
            } catch (PatientNotFoundException | NumberFormatException exception) {
                System.err.println("\r\nINVALID ENTRY");
            }
        }
    }
}
