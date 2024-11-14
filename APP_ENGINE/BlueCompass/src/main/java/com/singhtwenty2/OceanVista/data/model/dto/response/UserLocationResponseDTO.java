package com.singhtwenty2.OceanVista.data.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserLocationResponseDTO {
    private double latitude;
    private double longitude;
}
