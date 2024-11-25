package com.singhtwenty2.OceanVistaBusiness.data.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponseDTO {
    private String successMessage = null;
    private String token = null;
    private String errorMessage = null;
}
