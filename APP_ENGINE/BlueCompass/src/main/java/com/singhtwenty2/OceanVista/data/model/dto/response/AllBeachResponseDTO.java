package com.singhtwenty2.OceanVista.data.model.dto.response;

import com.singhtwenty2.OceanVista.data.model.entity.Beach;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AllBeachResponseDTO {
    private String successMessage = null;
    private List<Beach> beach = null;
    private String errorMessage = null;
}
