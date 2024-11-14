package com.singhtwenty2.OceanVista.data.model.dto.internal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BatchLocationInternalDTO {
    private String token;
    private double latitude;
    private double longitude;
}
