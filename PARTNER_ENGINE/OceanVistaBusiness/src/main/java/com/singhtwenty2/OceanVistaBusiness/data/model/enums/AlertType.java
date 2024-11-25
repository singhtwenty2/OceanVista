package com.singhtwenty2.OceanVistaBusiness.data.model.enums;

public enum AlertType {
    HIGH_WAVE("High Wave"),
    STRONG_WIND("Strong Wind"),
    RIP_CURRENT("Rip Current"),
    SHARK_SIGHTING("Shark Sighting"),
    JELLYFISH("Jellyfish Sighting"),
    RED_TIDE("Red Tide"),
    OTHER("Other");

    private final String description;

    AlertType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
