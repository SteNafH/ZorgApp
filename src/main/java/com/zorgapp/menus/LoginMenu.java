package com.zorgapp.menus;

import com.zorgapp.data.Data;
import com.zorgapp.exceptions.PatientNotFoundException;
import com.zorgapp.menus.admin.AdminMenu;
import com.zorgapp.menus.patient.PatientMenu;
import com.zorgapp.models.Language;
import com.zorgapp.models.Patient;

import java.util.Scanner;

public class LoginMenu implements Menu {
    private final Data data;
    private Menu menu;
    private final Language language;

    public LoginMenu(Language language) {
        this.data = Data.getInstance();
        this.language = language;
    }

    public void show() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\r\nENTER USER ID:");
            String input = scanner.nextLine();

            if (input.equals("0")) {
                this.menu = new AdminMenu(this.language);
                return;
            }

            try {
                Patient patient = this.data.getPatient(Integer.parseInt(input));
                this.menu = new PatientMenu(this.language, patient);

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
