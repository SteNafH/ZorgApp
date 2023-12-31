package com.zorgapp.models;

import com.zorgapp.languages.Languages;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;

public class Patient {
    private final int id;
    private String surName;
    private String firstName;
    private String callName;
    private LocalDate dateOfBirth;
    private final ArrayList<Weight> weightList;
    private double height;
    private final ArrayList<PatientMedicine> medicineList;

    public Patient(int id, String surName, String firstName, String callName, LocalDate dateOfBirth, ArrayList<Weight> weightList, double height, ArrayList<PatientMedicine> medicineList) {
        this.id = id;
        this.surName = surName;
        this.firstName = firstName;
        this.callName = callName;
        this.dateOfBirth = dateOfBirth;
        this.weightList = weightList;
        this.height = height;
        this.medicineList = medicineList;
    }

    public int getId() {
        return this.id;
    }

    public String getSurName() {
        return this.surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getCallName() {
        return this.callName;
    }

    public void setCallName(String callName) {
        this.callName = callName;
    }

    public LocalDate getDateOfBirth() {
        return this.dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public ArrayList<Weight> getWeightList() {
        return this.weightList;
    }

    public void addWeight(double kg) {
        Weight weight = new Weight(kg, LocalDate.now());
        this.weightList.add(0, weight);
    }

    public double getHeight() {
        return this.height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public ArrayList<PatientMedicine> getMedicineList() {
        return this.medicineList;
    }

    public void addMedicine(PatientMedicine medicine) {
        this.medicineList.add(medicine);
    }

    public double calcBMI(double height, double weight) {
        return Math.round((weight / (height * height)) * 10) / 10.0;
    }

    public HealthStatus getHealthStatus(double bmi) {
        if (bmi <= 18.5) {
            return HealthStatus.UNDERWEIGHT;
        } else if (bmi <= 25) {
            return HealthStatus.HEALTHY;
        } else if (bmi <= 30) {
            return HealthStatus.OVERWEIGHT;
        } else {
            return HealthStatus.OBESE;
        }
    }

    @Override
    public String toString() {
        Period age = this.dateOfBirth.until(LocalDate.now());
        return Languages.getString("patientNumber") + ": " + this.id + ", " + this.firstName + " " + this.surName + ", " + this.dateOfBirth + " (" + age.getYears() + ")";
    }

    public String toLongString() {
        Period age = this.dateOfBirth.until(LocalDate.now());
        Weight weight = this.weightList.get(0);
        double bmi = this.calcBMI(this.height, weight.getWeight());

        return "\r\n" + Languages.getString("surName") + ": " + this.surName +
                "\r\n" + Languages.getString("firstName") + ": " + this.firstName +
                "\r\n" + Languages.getString("callName") + ": " + this.callName +
                "\r\n" + Languages.getString("dateOfBirth") + ": " +  this.dateOfBirth + " (" + Languages.getString("age") + ": " + age.getYears() + ")" +
                "\r\n" + Languages.getString("currentWeight") + ": " +  weight.getWeight() + " KG, (" + Languages.getString("measuredOn") + ": " + weight.getDate() + ")" +
                "\r\n" + Languages.getString("height") + ": " +  this.height +
                "\r\n" + Languages.getString("bmi") + ": " +  bmi + " (" + this.getHealthStatus(bmi).getDescription() + ")" +
                "\r\n" + Languages.getString("medicine") + ": " + String.join(", ", this.medicineList.stream().map(PatientMedicine::toString).toList());
    }
}
