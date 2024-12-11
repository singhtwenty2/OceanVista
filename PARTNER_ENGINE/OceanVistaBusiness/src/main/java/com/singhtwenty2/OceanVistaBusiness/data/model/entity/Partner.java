package com.singhtwenty2.OceanVistaBusiness.data.model.entity;

import com.singhtwenty2.OceanVistaBusiness.data.model.entity.refrenced.BaseEntity;
import com.singhtwenty2.OceanVistaBusiness.data.model.entity.refrenced.Beach;
import com.singhtwenty2.OceanVistaBusiness.data.model.enums.PartnerStatus;
import com.singhtwenty2.OceanVistaBusiness.data.model.enums.PartnerType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Partner extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    @Column(unique = true)
    private String email;

    private String phoneNumber;
    private String password;

    @Enumerated(EnumType.STRING)
    private PartnerType partnerType;

    @Enumerated(EnumType.STRING)
    private PartnerStatus status;

    @ManyToMany
    @JoinTable(
            name = "partner_beaches",
            joinColumns = @JoinColumn(name = "partner_id"),
            inverseJoinColumns = @JoinColumn(name = "beach_id")
    )
    private Set<Beach> beaches;
    private int maxBeachCount;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "plan_id")
    private SubscriptionPlan subscriptionPlan;
}
