package com.singhtwenty2.OceanVista.data.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BeachResponseDTO {
    private Long id;
    private String name;
    private double latitude;
    private double longitude;
    private String region;
    private LocalTime openingTime;
    private LocalTime closingTime;
    private List<String> activities;
    private List<String> photos;
}
