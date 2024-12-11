package com.singhtwenty2.OceanVistaBusiness.data.model.entity;

import com.singhtwenty2.OceanVistaBusiness.data.model.entity.refrenced.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubscriptionPlan extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long planId;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    private double price;

    @Column(nullable = false)
    private int maxShops;

    @Column(nullable = false)
    private int maxServices;

    @Column(nullable = false)
    private double platformFee;

    @Column(columnDefinition = "TEXT")
    private String additionalFeatures;

    @ElementCollection
    @CollectionTable(name = "included_features", joinColumns = @JoinColumn(name = "plan_id"))
    @Column(name = "features", columnDefinition = "TEXT")
    private List<String> includedFeatures;

    @ElementCollection
    @CollectionTable(name = "excluded_features", joinColumns = @JoinColumn(name = "plan_id"))
    @Column(name = "features", columnDefinition = "TEXT")
    private List<String> excludedFeatures;
}