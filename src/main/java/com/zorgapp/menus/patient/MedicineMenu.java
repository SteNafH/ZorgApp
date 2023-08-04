package com.zorgapp.menus.patient;

import com.zorgapp.menus.Menu;
import com.zorgapp.models.Patient;

public class MedicineMenu implements Menu {
    private final Patient patient;

    public MedicineMenu(Patient patient) {
        this.patient = patient;
    }

    @Override
    public void show() {
        String string = "\r\n-----------------------------------------------" +
                String.join("\r\n-----------------------------------------------", this.patient.getMedicineList().stream().map(patientMedicine -> "\r\n" + patientMedicine.getMedicine().getInfo()).toList()) +
                "\r\n-----------------------------------------------";
        System.out.println(string);
    }
}
