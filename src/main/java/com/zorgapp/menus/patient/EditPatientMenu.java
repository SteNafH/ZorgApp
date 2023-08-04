package com.zorgapp.menus.patient;

import com.zorgapp.data.Data;
import com.zorgapp.menus.Menu;
import com.zorgapp.models.Language;
import com.zorgapp.models.Patient;

import java.util.Scanner;

public class EditPatientMenu implements Menu {
    private final Data data;
    private final Language language;
    private final Patient patient;

    public EditPatientMenu(Language language, Patient patient) {
        this.data = Data.getInstance();
        this.language = language;
        this.patient = patient;
    }

    @Override
    public void show() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String string = "\r\n-----------------------------------------------" +
                    "\r\n0 - RETURN" +
                    "\r\n1 - CALL NAME:              " + this.patient.getCallName() +
                    "\r\n-----------------------------------------------" +
                    "\r\nCHOOSE OPTION:";

            System.out.println(string);
            String input = scanner.nextLine();

            switch (input) {
                case "0" -> {
                    return;
                }
                case "1" -> {
                    System.out.println("\r\nENTER NEW CALL NAME:");
                    String callName = scanner.nextLine();
                    this.patient.setCallName(callName);
                }
                default -> {
                    System.err.println("\r\nINVALID INPUT");
                    continue;
                }
            }

            this.data.updatePatient(patient);
        }
    }
}
