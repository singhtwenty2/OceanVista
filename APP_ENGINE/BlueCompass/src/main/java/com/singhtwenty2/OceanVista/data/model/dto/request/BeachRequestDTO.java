package com.singhtwenty2.OceanVista.data.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BeachRequestDTO {
    private String name;
    private double latitude;
    private double longitude;
    private String region;
    private String description;
    private List<String> photos;

    private List<String> activities;
    private List<String> amenities;
    private LocalTime openingTime;
    private LocalTime closingTime;
    private int averageCrowdCount;
    private String peakTimes;
    private Map<String, String> seasonalActivities;
    private List<String> facilities;
    private List<String> services;
    private boolean accessibleForDisabled;
    private String weatherForecast;
    private boolean petFriendly;
    private List<String> foodAndBeverageOptions;
    private boolean shadeAvailable;
    private String parkingInfo;
    private String surfConditions;
    private boolean lifeguardsAvailable;
    private boolean familyFriendly;
    private boolean wifiAvailable;
    private String beachType;
    private List<String> rulesAndRegulations;
    private String emergencyContactInfo;
}
