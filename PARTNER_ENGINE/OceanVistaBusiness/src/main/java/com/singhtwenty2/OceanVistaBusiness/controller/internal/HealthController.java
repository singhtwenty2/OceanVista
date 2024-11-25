package com.singhtwenty2.OceanVistaBusiness.controller.internal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/partner.engine.api/v1")
public class HealthController {

    @GetMapping("/health")
    public String health() {
        return "Hemlo Saar, I am somehow alive 🤡";
    }

    @GetMapping("/ping")
    public String ping() {
        return "pong";
    }
}
