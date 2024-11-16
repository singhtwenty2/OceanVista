package com.singhtwenty2.OceanVista.data.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Beach extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "beach_id")
    private Long id;
    private String name;
    private double latitude;
    private double longitude;
    private String region;
    @Column(columnDefinition = "TEXT")
    private String description;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "beach_detail_id")
    private BeachDetail beachDetail;

    @OneToMany(mappedBy = "beach", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<BeachCondition> conditions;

    @OneToMany(mappedBy = "beach", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Resort> resorts;

    @OneToMany(mappedBy = "beach", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<MedicalService> medicalServices;

    @OneToMany(mappedBy = "beach", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Alert> alerts;

    @ElementCollection
    @CollectionTable(name = "beach_photos", joinColumns = @JoinColumn(name = "beach_id"))
    @Column(name = "photo_url", columnDefinition = "TEXT")
    private List<String> photos;

}


