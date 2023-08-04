package com.zorgapp.menus;

import com.zorgapp.data.Data;
import com.zorgapp.exceptions.PatientNotFoundException;
import com.zorgapp.menus.admin.AdminMenu;
import com.zorgapp.menus.patient.PatientMenu;
import com.zorgapp.models.Patient;

import java.util.Scanner;

public class LoginMenu implements Menu {
    private Menu menu;

    public void show() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\r\nENTER USER ID:");
            String input = scanner.nextLine();

            if (input.equals("0")) {
                this.menu = new AdminMenu();
                return;
            }

            try {
                Patient patient = Data.getPatient(Integer.parseInt(input));
                this.menu = new PatientMenu(patient);

                return;
            } catch (PatientNotFoundException exception) {
                System.err.println("\r\nLogin Failed");
            }
        }
    }

    public Menu getMenu() {
        return this.menu;
    }
}
