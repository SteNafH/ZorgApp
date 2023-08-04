package com.zorgapp.models;

import com.zorgapp.languages.Languages;

public enum HealthStatus {
    UNDERWEIGHT("underweight"),
    HEALTHY("healthyWeight"),
    OVERWEIGHT("overweight"),
    OBESE("obesity");

    private final String key;

    HealthStatus(String key) {
        this.key = key;
    }

    public String getDescription() {
        return Languages.getString(this.key);
    }
}
