package com.zorgapp.menus.admin;

import com.zorgapp.data.Data;
import com.zorgapp.menus.Menu;
import com.zorgapp.menus.patient.MedicineMenu;
import com.zorgapp.models.Language;
import com.zorgapp.models.Patient;

import java.util.Scanner;

public class PatientMenu implements Menu {
    private final Data data;
    private final Language language;
    private final Patient patient;

    public PatientMenu(Language language, Patient patient) {
        this.data = Data.getInstance();
        this.language = language;
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
                case "1" -> new EditPatientMenu(this.language, this.patient).show();
                case "2" -> new WeightMenu(this.language, this.patient).show();
                case "3" -> new MedicineMenu(this.language, this.patient).show();
                case "4" -> {
                    this.data.deletePatient(patient);
                    return;
                }
                default -> System.err.println("\r\nINVALID INPUT");
            }
        }
    }
}
