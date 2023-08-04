package com.zorgapp.menus.admin;

import com.zorgapp.data.Data;
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
                    "\r\n0 - RETURN" +
                    "\r\n1 - EDIT PATIENT" +
                    "\r\n2 - SHOW WEIGHT PROGRESS" +
                    "\r\n3 - MEDICINE INFO" +
                    "\r\n4 - DELETE PATIENT" +
                    "\r\n\r\nENTER CHOICE:";

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
                default -> System.err.println("\r\nINVALID INPUT");
            }
        }
    }
}
