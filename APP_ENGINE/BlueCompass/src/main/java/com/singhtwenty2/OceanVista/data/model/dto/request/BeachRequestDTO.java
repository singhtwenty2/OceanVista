package com.singhtwenty2.OceanVista.data.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BeachRequestDTO {
    private String name;
    private double latitude;
    private double longitude;
    private String region;
    private String description;
}
