package com.singhtwenty2.OceanVistaBusiness.data.model.entity;

import com.singhtwenty2.OceanVistaBusiness.data.model.entity.refrenced.BaseEntity;
import com.singhtwenty2.OceanVistaBusiness.data.model.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Payment extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "subscription_id", nullable = false)
    private Subscription subscription;

    private String paymentGatewayId;
    private double amount;
    private LocalDateTime paymentDate;
    private String currency;
    private PaymentStatus status;
}

