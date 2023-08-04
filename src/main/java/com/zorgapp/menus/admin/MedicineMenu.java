package com.zorgapp.menus.admin;

import com.zorgapp.data.Data;
import com.zorgapp.menus.Menu;
import com.zorgapp.models.Medicine;
import com.zorgapp.models.Patient;
import com.zorgapp.models.PatientMedicine;

import java.util.ArrayList;
import java.util.Scanner;

public class MedicineMenu implements Menu {
    private final Patient patient;

    public MedicineMenu(Patient patient) {
        this.patient = patient;
    }

    @Override
    public void show() {
        Scanner scanner = new Scanner(System.in);
        ArrayList<PatientMedicine> medicineList = this.patient.getMedicineList();

        while (true) {
            String string = "\r\n-----------------------------------------------" +
                    String.join("\r\n-----------------------------------------------", medicineList.stream().map(patientMedicine -> "\r\n" + patientMedicine.getAmount() + " mg " + patientMedicine.getMedicine().getName()).toList()) +
                    "\r\n-----------------------------------------------" +
                    "\r\n0 - RETURN" +
                    "\r\n1 - EDIT MEDICINE" +
                    "\r\n2 - ADD MEDICINE" +
                    "\r\n3 - DELETE MEDICINE";
            System.out.println(string);

            String input = scanner.nextLine();
            switch (input) {
                case "0" -> {
                    return;
                }
                case "1" -> {
                    PatientMedicine patientMedicine = this.selectPatientMedicineMenu(this.patient.getMedicineList(), "\r\nCHOOSE MEDICINE TO EDIT");

                    if (patientMedicine == null) {
                        continue;
                    }

                    Medicine medicine = this.selectMedicineMenu();

                    System.out.println("\r\nENTER AMOUNT (mg):");
                    int mg = Integer.parseInt(scanner.nextLine());

                    patientMedicine.setAmount(mg);
                    patientMedicine.setMedicine(medicine);
                }
                case "2" -> {
                    Medicine medicine = this.selectMedicineMenu();

                    System.out.println("\r\nENTER AMOUNT (mg):");
                    int mg = Integer.parseInt(scanner.nextLine());

                    PatientMedicine patientMedicine = new PatientMedicine(mg, medicine);
                    this.patient.addMedicine(patientMedicine);
                }
                case "3" -> {
                    PatientMedicine medicine = this.selectPatientMedicineMenu(this.patient.getMedicineList(), "\r\nCHOOSE MEDICINE TO DELETE");

                    if (medicine != null) {
                        medicineList.remove(medicine);
                    }
                }
                default -> {
                    System.err.println("\r\nINVALID INPUT");
                    continue;
                }
            }

            Data.updatePatient();
        }
    }

    private Medicine selectMedicineMenu() {
        Scanner scanner = new Scanner(System.in);
        Medicine[] medicineList = Medicine.values();

        while (true) {
            StringBuilder builder = new StringBuilder();
            builder.append("\r\n-----------------------------------------------")
                    .append("\r\n0 - RETURN");

            for (int i = 0; i < medicineList.length; i++) {
                Medicine medicine = medicineList[i];
                builder.append("\r\n").append(i + 1).append(" - ").append(medicine.getName().toUpperCase()).append("\t\t(").append(medicine.getInfo()).append(")");
            }

            builder.append("\r\n-----------------------------------------------")
                    .append("\r\nCHOOSE MEDICINE:");
            System.out.println(builder);

            String input = scanner.nextLine();

            if (input.equals("0")) {
                return null;
            }

            try {
                int index = Integer.parseInt(input);
                Medicine medicine = medicineList[index - 1];

                if (medicine != null) {
                    return medicine;
                }

                System.err.println("\r\nINVALID INPUT");
            } catch (NumberFormatException exception) {
                System.err.println("\r\nINVALID INPUT");
            }
        }
    }

    private PatientMedicine selectPatientMedicineMenu(ArrayList<PatientMedicine> medicineList, String chooseText) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            StringBuilder builder = new StringBuilder();
            builder.append("\r\n-----------------------------------------------")
                    .append("\r\n0 - RETURN");

            for (int i = 0; i < medicineList.size(); i++) {
                PatientMedicine medicine = medicineList.get(i);
                builder.append("\r\n").append(i + 1).append(" - ").append(medicine.getAmount()).append(" mg ").append(medicine.getMedicine().getName());
            }

            builder.append("\r\n-----------------------------------------------")
                    .append(chooseText);
            System.out.println(builder);

            String input = scanner.nextLine();

            if (input.equals("0")) {
                return null;
            }

            try {
                int index = Integer.parseInt(input);
                PatientMedicine medicine = medicineList.get(index - 1);

                if (medicine != null) {
                    return medicine;
                }

                System.err.println("\r\nINVALID INPUT");
            } catch (NumberFormatException exception) {
                System.err.println("\r\nINVALID INPUT");
            }
        }
    }
}
