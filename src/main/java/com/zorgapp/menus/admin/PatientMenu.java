package com.zorgapp.menus.admin;

import com.zorgapp.data.Data;
import com.zorgapp.languages.Languages;
import com.zorgapp.menus.Menu;
import com.zorgapp.menus.patient.MedicineMenu;
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
                    "\r\n0 - " + Languages.getString("return") +
                    "\r\n1 - " + Languages.getString("editPatient") +
                    "\r\n2 - " + Languages.getString("showWeightProgress") +
                    "\r\n3 - " + Languages.getString("medicineInfo") +
                    "\r\n4 - " + Languages.getString("deletePatient") +
                    "\r\n\r\n" + Languages.getString("chooseOption") + ":";

            System.out.println(string);
            String input = scanner.nextLine();

            switch (input) {
                case "0" -> {
                    return;
                }
                case "1" -> new EditPatientMenu(this.patient).show();
                case "2" -> new WeightMenu(this.patient).show();
                case "3" -> new MedicineMenu(this.patient).show();
                case "4" -> {
                    Data.deletePatient(patient);
                    return;
                }
                default -> System.err.println("\r\n" + Languages.getString("invalidInput"));
            }
        }
    }
}
