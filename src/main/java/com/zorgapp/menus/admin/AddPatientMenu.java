package com.zorgapp.menus.admin;

import com.zorgapp.data.Data;
import com.zorgapp.menus.Menu;
import com.zorgapp.models.Patient;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class AddPatientMenu implements Menu {
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
            id = Data.getPatients().stream().max(Comparator.comparing(Patient::getId)).get().getId() + 1;
        } catch (NoSuchElementException exception) {
            id = 1;
        }

        Patient patient = new Patient(id, surName, firstName, callName, dateOfBirth, new ArrayList<>(), height, new ArrayList<>());
        patient.addWeight(weight);

        Data.addPatient(patient);
    }
}
