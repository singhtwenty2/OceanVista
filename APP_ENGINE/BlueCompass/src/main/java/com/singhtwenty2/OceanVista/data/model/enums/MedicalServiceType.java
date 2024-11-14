package com.singhtwenty2.OceanVista.data.model.enums;

public enum MedicalServiceType {
    FIRST_AID("First Aid"),
    LIFEGUARD("Lifeguard"),
    AMBULANCE("Ambulance"),
    HOSPITAL("Hospital"),
    OTHER("Other");

    private final String description;

    MedicalServiceType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
