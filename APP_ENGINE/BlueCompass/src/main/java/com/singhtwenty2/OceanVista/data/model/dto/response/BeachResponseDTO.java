package com.singhtwenty2.OceanVista.data.model.dto.response;

import com.singhtwenty2.OceanVista.data.model.entity.Beach;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BeachResponseDTO {
    private String successMessage = null;
    private Beach beach = null;
    private String errorMessage = null;
}
