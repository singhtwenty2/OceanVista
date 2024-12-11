package com.singhtwenty2.OceanVistaBusiness.data.model.entity;

import com.singhtwenty2.OceanVistaBusiness.data.model.entity.refrenced.BaseEntity;
import com.singhtwenty2.OceanVistaBusiness.data.model.entity.refrenced.Beach;
import com.singhtwenty2.OceanVistaBusiness.data.model.enums.MedicalServiceType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MedicalService extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String phoneNumber;
    private String email;
    private double latitude;
    private double longitude;
    private MedicalServiceType serviceType;

    @ManyToOne
    @JoinColumn(name = "beach_id", nullable = false)
    private Beach beach;

    @ElementCollection
    @CollectionTable(name = "medical_service_photos", joinColumns = @JoinColumn(name = "medical_service_id"))
    @Column(name = "photo_url", columnDefinition = "TEXT")
    private List<String> photos;
}
