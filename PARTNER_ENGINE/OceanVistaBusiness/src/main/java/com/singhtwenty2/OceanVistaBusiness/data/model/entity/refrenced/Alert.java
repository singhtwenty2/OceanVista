package com.singhtwenty2.OceanVistaBusiness.data.model.entity.refrenced;

import com.singhtwenty2.OceanVistaBusiness.data.model.enums.AlertType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Alert extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "alert_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private AlertType alertType;

    private String description;
    private LocalDateTime issuedAt;
    private LocalDateTime expiresAt;

    @ManyToOne
    @JoinColumn(name = "beach_id", nullable = false)
    private Beach beach;
}

