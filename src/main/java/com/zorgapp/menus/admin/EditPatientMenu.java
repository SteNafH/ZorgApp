package com.zorgapp.menus.admin;

import com.zorgapp.data.Data;
import com.zorgapp.menus.Menu;
import com.zorgapp.models.Patient;
import com.zorgapp.models.PatientMedicine;
import com.zorgapp.models.Weight;

import java.time.LocalDate;
import java.time.Period;
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
            Period age = this.patient.getDateOfBirth().until(LocalDate.now());
            Weight weight = this.patient.getWeightList().get(0);

            String string = "\r\n-----------------------------------------------" +
                    "\r\n0 - RETURN" +
                    "\r\n1 - SURNAME:                " + this.patient.getSurName() +
                    "\r\n2 - FIRST NAME:             " + this.patient.getFirstName() +
                    "\r\n3 - CALL NAME:              " + this.patient.getCallName() +
                    "\r\n4 - DATE OF BIRTH:          " + this.patient.getDateOfBirth() + " (Age: " + age.getYears() + ")" +
                    "\r\n5 - CURRENT WEIGHT:         " + weight.getWeight() + " KG, (Measured on: " + weight.getDate() + ")" +
                    "\r\n6 - HEIGHT:                 " + this.patient.getHeight() +
                    "\r\n7 - MEDICINE:               " + String.join(", ", this.patient.getMedicineList().stream().map(PatientMedicine::toString).toList()) +
                    "\r\n-----------------------------------------------" +
                    "\r\nCHOOSE OPTION:";

            System.out.println(string);
            String input = scanner.nextLine();

            switch (input) {
                case "0" -> {
                    return;
                }
                case "1" -> {
                    System.out.println("\r\nENTER NEW SURNAME:");
                    String surName = scanner.nextLine();
                    this.patient.setSurName(surName);
                }
                case "2" -> {
                    System.out.println("\r\nENTER NEW FIRST NAME:");
                    String firstName = scanner.nextLine();
                    this.patient.setFirstName(firstName);
                }
                case "3" -> {
                    System.out.println("\r\nENTER NEW CALL NAME:");
                    String callName = scanner.nextLine();
                    this.patient.setCallName(callName);
                }
                case "4" -> {
                    System.out.println("\r\nENTER NEW DATE OF BIRTH (YYYY-MM-DD):");
                    String dateOfBirth = scanner.nextLine();
                    this.patient.setDateOfBirth(LocalDate.parse(dateOfBirth));
                }
                case "5" -> {
                    System.out.println("\r\nENTER NEW WEIGHT (KG):");
                    String kg = scanner.nextLine();
                    this.patient.addWeight(Double.parseDouble(kg));
                }
                case "6" -> {
                    System.out.println("\r\nENTER NEW HEIGHT:");
                    String height = scanner.nextLine();
                    this.patient.setHeight(Double.parseDouble(height));
                }
                case "7" -> new MedicineMenu(this.patient).show();
                default -> {
                    System.err.println("\r\nINVALID INPUT");
                    continue;
                }
            }
            Data.updatePatient();
        }
    }
}
