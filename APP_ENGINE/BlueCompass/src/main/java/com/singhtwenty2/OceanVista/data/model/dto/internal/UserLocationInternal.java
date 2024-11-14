package com.singhtwenty2.OceanVista.data.model.dto.internal;

import com.singhtwenty2.OceanVista.data.model.entity.UserLocation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserLocationInternal {
    private String successMessage = null;
    private String errorMessage = null;
    private UserLocation locationResponse = null;
}
