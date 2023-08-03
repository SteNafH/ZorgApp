package com.zorgapp.models;

public enum HealthStatus {
    UNDERWEIGHT("Underweight"),
    HEALTHY("Healthy weight"),
    OVERWEIGHT("Overweight"),
    OBESE("Obesity");

    private String description;

    HealthStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
