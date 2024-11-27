package com.singhtwenty2.OceanVista.data.model.entity;

import com.singhtwenty2.OceanVista.data.model.enums.PlanType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Subscription extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "partner_id", nullable = false)
    private Partner partner;

    @Enumerated(EnumType.STRING)
    private PlanType planType;

    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private boolean isActive;

    @OneToOne(mappedBy = "subscription", cascade = CascadeType.ALL)
    private Payment payment;
}
