package com.singhtwenty2.OceanVistaBusiness.data.model.dto.request;

import com.singhtwenty2.OceanVistaBusiness.data.model.enums.PartnerType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterPartnerRequestDTO {
    private String name;
    private String email;
    private String phoneNumber;
    private String password;
    private PartnerType partnerType;
}
