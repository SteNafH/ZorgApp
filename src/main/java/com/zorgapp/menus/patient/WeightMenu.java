package com.zorgapp.menus.patient;

import com.zorgapp.menus.Menu;
import com.zorgapp.models.Language;
import com.zorgapp.models.Patient;
import com.zorgapp.models.Weight;

public class WeightMenu implements Menu {
    private Language language;
    private Patient patient;

    public WeightMenu(Language language, Patient patient) {
        this.language = language;
        this.patient = patient;
    }

    @Override
    public void show() {
        StringBuilder builder = new StringBuilder();
        builder.append("\r\n-----------------------------------------------");

        for (Weight weight : this.patient.getWeightList()) {
            builder.append("\r\n")
                    .append(weight.getDate())
                    .append(",\t")
                    .append(weight.getWeight())
                    .append("KG: \t");

            for (int i = 0; i < weight.getWeight() / 2; i++) {
                builder.append("*");
            }
        }

        builder.append("\r\n-----------------------------------------------");
        System.out.println(builder);
    }
}
