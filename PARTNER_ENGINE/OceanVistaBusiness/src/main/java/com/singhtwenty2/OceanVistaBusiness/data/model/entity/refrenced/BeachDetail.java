package com.singhtwenty2.OceanVistaBusiness.data.model.entity.refrenced;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.List;
import java.util.Map;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BeachDetail extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ElementCollection
    @CollectionTable(name = "beach_activities", joinColumns = @JoinColumn(name = "beach_detail_id"))
    private List<String> activities;

    @ElementCollection
    @CollectionTable(name = "beach_amenities", joinColumns = @JoinColumn(name = "beach_detail_id"))
    private List<String> amenities;

    private LocalTime openingTime;
    private LocalTime closingTime;

    private int averageCrowdCount;
    private String peakTimes;

    @ElementCollection
    @CollectionTable(name = "seasonal_activities", joinColumns = @JoinColumn(name = "beach_detail_id"))
    @MapKeyColumn(name = "season")
    private Map<String, String> seasonalActivities;

    @ElementCollection
    @CollectionTable(name = "beach_facilities", joinColumns = @JoinColumn(name = "beach_detail_id"))
    private List<String> facilities;

    @ElementCollection
    @CollectionTable(name = "beach_services", joinColumns = @JoinColumn(name = "beach_detail_id"))
    private List<String> services;

    private boolean accessibleForDisabled;
    private String weatherForecast;
    private boolean petFriendly;

    @ElementCollection
    @CollectionTable(name = "food_and_beverage_options", joinColumns = @JoinColumn(name = "beach_detail_id"))
    private List<String> foodAndBeverageOptions;

    private boolean shadeAvailable;
    private String parkingInfo;
    private String surfConditions;
    private boolean lifeguardsAvailable;
    private boolean familyFriendly;
    private boolean wifiAvailable;
    private String beachType;

    @ElementCollection
    @CollectionTable(name = "beach_rules", joinColumns = @JoinColumn(name = "beach_detail_id"))
    private List<String> rulesAndRegulations;

    private String emergencyContactInfo;
}