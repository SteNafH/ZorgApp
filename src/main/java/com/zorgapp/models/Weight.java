package com.zorgapp.models;

import java.time.LocalDate;

public class Weight {
    private double weight;
    private LocalDate date;

    public Weight(double weight, LocalDate date) {
        this.weight = weight;
        this.date = date;
    }

    public double getWeight() {
        return this.weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public LocalDate getDate() {
        return this.date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
