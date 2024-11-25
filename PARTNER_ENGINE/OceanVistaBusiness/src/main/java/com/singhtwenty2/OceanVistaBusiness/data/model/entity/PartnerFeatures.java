package com.singhtwenty2.OceanVistaBusiness.data.model.entity;

import com.singhtwenty2.OceanVistaBusiness.data.model.entity.refrenced.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PartnerFeatures extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "partner_id", nullable = false, unique = true)
    private Partner partner;

    // Feature flags
    private boolean analyticsEnabled;
    private boolean prioritySupport;
    private boolean bookingManagementEnabled;
    private boolean enhancedListingCustomization;
    private boolean customerReviewManagement;
    private boolean apiAccess;

    // Listing limits
    private int maxListings;
    private boolean prominentPlacement;
    private boolean realTimeAnalytics;
    private boolean customIntegrationEnabled;

    // Additional perks
    private boolean dedicatedAccountManager;
    private boolean communitySupport;
}