package com.singhtwenty2.OceanVistaBusiness.data.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PartnerDetailResponseDTO {
    private String userId;
    private String subscriptionPlanName;
    private String name;
    private String email;
    private String phoneNumber;
    private String partnerType;
    private String partnerStatus;
    private int maxAllowedBeachCount;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
