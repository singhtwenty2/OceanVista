package com.singhtwenty2.OceanVista.data.model.entity;

import com.singhtwenty2.OceanVista.data.model.enums.BeachSafetyRecommendation;
import com.singhtwenty2.OceanVista.data.model.enums.CrowdDensity;
import com.singhtwenty2.OceanVista.data.model.enums.SafetyFlagColor;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BeachCondition extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "condition_id")
    private Long id;

    private double waveHeight;
    private double windSpeed;
    private String waterQuality;
    private LocalDateTime timestamp;
    private double airTemperature;
    private double waterTemperature;
    private double pressure;
    private double humidity;
    private double uvIndex;
    private double visibility;
    private String tideCondition;
    private String windDirection;
    private double pollenCount;

    @Enumerated(EnumType.STRING)
    private CrowdDensity crowdDensity;

    @Enumerated(EnumType.STRING)
    private SafetyFlagColor safetyFlags;

    @Enumerated(EnumType.STRING)
    private BeachSafetyRecommendation finalRemark;

    @ManyToOne
    @JoinColumn(name = "beach_id", nullable = false)
    private Beach beach;
}
