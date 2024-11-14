package com.singhtwenty2.OceanVista.data.model.enums;

public enum BeachSafetyRecommendation {
    SAFE("Conditions are stable. The beach is safe for all activities."),
    CAUTION("Moderate risk: Be alert to minor hazards. Proceed with caution."),
    DANGEROUS("High risk: Unsafe conditions detected. Avoid beach activities."),
    CLOSED("Beach access restricted due to hazardous conditions. Do not enter."),
    UNKNOWN("Condition assessment unavailable. Verify with local authorities before proceeding.");

    private final String description;

    BeachSafetyRecommendation(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}