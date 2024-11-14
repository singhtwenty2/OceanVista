package com.singhtwenty2.OceanVista.data.model.dto.request;

import com.singhtwenty2.OceanVista.data.model.enums.NotificationPreference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequestDTO {
    private String email;
    private String name;
    private String phoneNumber;
    private String password;
    private NotificationPreference notificationPreference;
}
