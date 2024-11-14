package com.singhtwenty2.OceanVista.data.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserLocation extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "location_id")
    private Long id;

    private double latitude;
    private double longitude;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true, updatable = false)
    private Users user;
}

