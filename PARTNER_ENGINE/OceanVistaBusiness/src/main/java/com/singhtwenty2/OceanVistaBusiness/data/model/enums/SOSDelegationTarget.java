package com.singhtwenty2.OceanVistaBusiness.data.model.enums;

public enum SOSDelegationTarget {
    LOCAL_VENDOR("Delegate to local vendors for on-site assistance"),
    USER("Alert nearby users for immediate assistance"),
    MEDICAL_SERVICE("Escalate to hospitals or emergency medical services");

    private final String description;

    SOSDelegationTarget(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
