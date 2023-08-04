package com.zorgapp.menus.admin;

import com.zorgapp.data.Data;
import com.zorgapp.languages.Languages;
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
                    "\r\n0 - " + Languages.getString("return") + ": " +
                    "\r\n1 - " + Languages.getString("surName") + ": " + this.patient.getSurName() +
                    "\r\n2 - " + Languages.getString("firstName") + ": " + this.patient.getFirstName() +
                    "\r\n3 - " + Languages.getString("callName") + ": " + this.patient.getCallName() +
                    "\r\n4 - " + Languages.getString("dateOfBirth") + ": " + this.patient.getDateOfBirth() + " (" + Languages.getString("age") + ": " + age.getYears() + ")" +
                    "\r\n5 - " + Languages.getString("currentWeight") + ": " + weight.getWeight() + " KG, (" + Languages.getString("measuredOn") + ": " + weight.getDate() + ")" +
                    "\r\n6 - " + Languages.getString("height") + ": " + this.patient.getHeight() +
                    "\r\n7 - " + Languages.getString("medicine") + ": " + String.join(", ", this.patient.getMedicineList().stream().map(PatientMedicine::toString).toList()) +
                    "\r\n-----------------------------------------------" +
                    "\r\n" + Languages.getString("chooseOption") + ":";

            System.out.println(string);
            String input = scanner.nextLine();

            switch (input) {
                case "0" -> {
                    return;
                }
                case "1" -> {
                    System.out.println("\r\n" + Languages.getString("enterNewSurName") + ":");
                    String surName = scanner.nextLine();
                    this.patient.setSurName(surName);
                }
                case "2" -> {
                    System.out.println("\r\n" + Languages.getString("enterNewFirstName") + ":");
                    String firstName = scanner.nextLine();
                    this.patient.setFirstName(firstName);
                }
                case "3" -> {
                    System.out.println("\r\n" + Languages.getString("enterNewCallName") + ":");
                    String callName = scanner.nextLine();
                    this.patient.setCallName(callName);
                }
                case "4" -> {
                    System.out.println("\r\n" + Languages.getString("enterNewDateOfBirth") + " (YYYY-MM-DD):");
                    String dateOfBirth = scanner.nextLine();
                    this.patient.setDateOfBirth(LocalDate.parse(dateOfBirth));
                }
                case "5" -> {
                    System.out.println("\r\n" + Languages.getString("enterNewWeight") + " (KG):");
                    String kg = scanner.nextLine();
                    this.patient.addWeight(Double.parseDouble(kg));
                }
                case "6" -> {
                    System.out.println("\r\n" + Languages.getString("enterNewHeight") + ":");
                    String height = scanner.nextLine();
                    this.patient.setHeight(Double.parseDouble(height));
                }
                case "7" -> new MedicineMenu(this.patient).show();
                default -> {
                    System.err.println("\r\n" + Languages.getString("invalidInput"));
                    continue;
                }
            }
            Data.updatePatient();
        }
    }
}
