package com.singhtwenty2.OceanVista.data.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDetailResponseDTO {
    private String user_id;
    private String username;
    private String name;
    private String phoneNumber;
    private String email;
    private String notificationPrefrence;
}
