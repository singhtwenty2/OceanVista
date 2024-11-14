package com.singhtwenty2.OceanVista.data.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserLocationRequestDTO {
    private double latitude;
    private double longitude;
}
