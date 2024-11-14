package com.singhtwenty2.OceanVista.data.model.entity;

import com.singhtwenty2.OceanVista.data.model.enums.SOSDelegationTarget;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SOSRequest extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double latitude;
    private double longitude;
    private LocalDateTime requestedAt;
    private String description;

    @Enumerated(EnumType.STRING)
    private SOSDelegationTarget delegationTarget;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Users user;

    @ManyToOne
    @JoinColumn(name = "beach_id")
    private Beach beach;
}
