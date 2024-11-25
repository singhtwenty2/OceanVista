package com.singhtwenty2.OceanVistaBusiness.util.auth;

import java.util.UUID;

public class ValidationToken {

    public static String generateVerificationToken() {
        return UUID.randomUUID().toString();
    }
}
