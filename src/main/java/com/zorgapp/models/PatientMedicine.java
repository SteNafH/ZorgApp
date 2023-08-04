package com.zorgapp.models;

public class PatientMedicine {
    private int amount;
    private Medicine medicine;

    public PatientMedicine(int amount, Medicine medicine) {
        this.amount = amount;
        this.medicine = medicine;
    }

    public int getAmount() {
        return this.amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Medicine getMedicine() {
        return medicine;
    }

    public void setMedicine(Medicine medicine) {
        this.medicine = medicine;
    }

    @Override
    public String toString() {
        return this.getAmount() + " mg " + this.medicine.getName();
    }
}
