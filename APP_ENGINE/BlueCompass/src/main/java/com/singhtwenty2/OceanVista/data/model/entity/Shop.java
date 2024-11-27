package com.singhtwenty2.OceanVista.data.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Shop extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String contactInfo;
    private double latitude;
    private double longitude;
    private String description;

    @ManyToOne
    @JoinColumn(name = "beach_id", nullable = false)
    private Beach beach;

    @ElementCollection
    @CollectionTable(name = "shop_photos", joinColumns = @JoinColumn(name = "shop_id"))
    @Column(name = "photo_url", columnDefinition = "TEXT")
    private List<String> photos;
}
