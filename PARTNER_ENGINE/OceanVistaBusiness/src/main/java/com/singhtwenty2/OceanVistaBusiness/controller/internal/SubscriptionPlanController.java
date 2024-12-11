package com.singhtwenty2.OceanVistaBusiness.controller.internal;

import com.singhtwenty2.OceanVistaBusiness.data.model.dto.response.AppResponseDTO;
import com.singhtwenty2.OceanVistaBusiness.data.model.dto.response.PartnerDetailResponseDTO;
import com.singhtwenty2.OceanVistaBusiness.data.model.entity.SubscriptionPlan;
import com.singhtwenty2.OceanVistaBusiness.service.plans.SubscriptionPlanService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/partner.engine.api/v1/subscription-plan")
public class SubscriptionPlanController {

    private final SubscriptionPlanService subscriptionPlanService;
    public SubscriptionPlanController(SubscriptionPlanService subscriptionPlanService) {
        this.subscriptionPlanService = subscriptionPlanService;
    }

    @PostMapping("/save")
    public ResponseEntity<AppResponseDTO> savePlan(
            @RequestBody SubscriptionPlan subscriptionPlan,
            HttpServletRequest request
    ) {
        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        String token = authHeader != null ? authHeader.substring(7).trim() : null;
        if(token == null) {
            ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new PartnerDetailResponseDTO());
        }
        boolean isSaved = subscriptionPlanService.savePlan(subscriptionPlan);
        if (isSaved) {
            return ResponseEntity.ok(new AppResponseDTO("Plan saved successfully", null));
        } else {
            return ResponseEntity.badRequest().body(new AppResponseDTO("Failed to save plan", null));
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<SubscriptionPlan>> getAllPlans() {
        return ResponseEntity.ok(subscriptionPlanService.getAllPlans());
    }
}
