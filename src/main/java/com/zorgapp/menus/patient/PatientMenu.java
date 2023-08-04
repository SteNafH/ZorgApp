package com.zorgapp.menus.patient;

import com.zorgapp.languages.Languages;
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
                    "\r\n0 - " + Languages.getString("stopProgram") +
                    "\r\n1 - " + Languages.getString("editData") +
                    "\r\n2 - " + Languages.getString("showWeightProgress") +
                    "\r\n3 - " + Languages.getString("medicineInfo") +
                    "\r\n\r\n" + Languages.getString("chooseOption") + ":";

            System.out.println(string);
            String input = scanner.nextLine();

            switch (input) {
                case "0" -> System.exit(0);
                case "1" -> new EditPatientMenu(this.patient).show();
                case "2" -> new WeightMenu(this.patient).show();
                case "3" -> new MedicineMenu(this.patient).show();
                default -> System.err.println("\r\n" + Languages.getString("invalidInput"));
            }
        }
    }
}
