package com.singhtwenty2.OceanVista.util.auth;

public class ExtractUsername {
    public static String extractUsernameFromEmail(String email) {
        return email.split("@")[0];
    }
}
