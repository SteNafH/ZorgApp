package com.zorgapp.menus.patient;

import com.zorgapp.data.Data;
import com.zorgapp.menus.Menu;
import com.zorgapp.models.Patient;

import java.util.Scanner;

public class EditPatientMenu implements Menu {
    private final Patient patient;

    public EditPatientMenu(Patient patient) {
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

            Data.updatePatient();
        }
    }
}
