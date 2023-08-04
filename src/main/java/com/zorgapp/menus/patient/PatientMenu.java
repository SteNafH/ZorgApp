package com.zorgapp.menus.patient;

import com.zorgapp.menus.Menu;
import com.zorgapp.models.Patient;

import java.util.Scanner;

public class PatientMenu implements Menu {
    private final Patient patient;

    public PatientMenu(Patient patient) {
        this.patient = patient;
    }

    @Override
    public void show() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String string = "\r\n-----------------------------------------------" +
                    this.patient.toLongString() +
                    "\r\n-----------------------------------------------" +
                    "\r\n0 - STOP PROGRAM" +
                    "\r\n1 - EDIT DATA" +
                    "\r\n2 - SHOW WEIGHT PROGRESS" +
                    "\r\n3 - MEDICINE INFO" +
                    "\r\n\r\nENTER CHOICE:";

            System.out.println(string);
            String input = scanner.nextLine();

            switch (input) {
                case "0" -> System.exit(0);
                case "1" -> new EditPatientMenu(this.patient).show();
                case "2" -> new WeightMenu(this.patient).show();
                case "3" -> new MedicineMenu(this.patient).show();
                default -> System.err.println("\r\nINVALID INPUT");
            }
        }
    }
}
