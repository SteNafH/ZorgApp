package com.zorgapp.menus.admin;

import com.zorgapp.data.Data;
import com.zorgapp.menus.Menu;
import com.zorgapp.models.Language;
import com.zorgapp.models.Patient;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class AddPatientMenu implements Menu {
    private final Data data;
    private final Language language;

    public AddPatientMenu(Language language) {
        this.data = Data.getInstance();
        this.language = language;
    }

    @Override
    public void show() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\r\nENTER SURNAME:");
        String surName = scanner.nextLine();

        System.out.println("\r\nENTER FIRST NAME:");
        String firstName = scanner.nextLine();

        System.out.println("\r\nENTER CALL NAME:");
        String callName = scanner.nextLine();

        System.out.println("\r\nENTER DATE OF BIRTH (YYYY-MM-DD):");
        LocalDate dateOfBirth = LocalDate.parse(scanner.nextLine());

        System.out.println("\r\nENTER WEIGHT (KG):");
        double weight = Double.parseDouble(scanner.nextLine());

        System.out.println("\r\nENTER HEIGHT (M):");
        double height = Double.parseDouble(scanner.nextLine());

        int id;

        try {
            id = this.data.getPatients().stream().max(Comparator.comparing(Patient::getId)).get().getId() + 1;
        } catch (NoSuchElementException exception) {
            id = 1;
        }

        Patient patient = new Patient(id, surName, firstName, callName, dateOfBirth, new ArrayList<>(), height, new ArrayList<>());
        patient.setWeight(weight);

        this.data.addPatient(patient);
    }
}
