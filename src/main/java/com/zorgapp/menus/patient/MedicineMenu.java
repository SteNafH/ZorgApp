package com.zorgapp.menus.patient;

import com.zorgapp.menus.Menu;
import com.zorgapp.models.Language;
import com.zorgapp.models.Patient;

public class MedicineMenu implements Menu {
    private final Language language;
    private final Patient patient;

    public MedicineMenu(Language language, Patient patient) {
        this.language = language;
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
