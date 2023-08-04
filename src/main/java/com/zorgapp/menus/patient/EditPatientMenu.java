package com.zorgapp.menus.patient;

import com.zorgapp.data.Data;
import com.zorgapp.languages.Languages;
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
                    "\r\n0 - " + Languages.getString("return") +
                    "\r\n1 - " + Languages.getString("callName") + ":" + this.patient.getCallName() +
                    "\r\n-----------------------------------------------" +
                    "\r\n"  + Languages.getString("chooseOption") + ":";

            System.out.println(string);
            String input = scanner.nextLine();

            switch (input) {
                case "0" -> {
                    return;
                }
                case "1" -> {
                    System.out.println("\r\n" + Languages.getString("enterNewCallName") +":");
                    String callName = scanner.nextLine();
                    this.patient.setCallName(callName);
                }
                default -> {
                    System.err.println("\r\n" + Languages.getString("invalidInput"));
                    continue;
                }
            }

            Data.updatePatient();
        }
    }
}
