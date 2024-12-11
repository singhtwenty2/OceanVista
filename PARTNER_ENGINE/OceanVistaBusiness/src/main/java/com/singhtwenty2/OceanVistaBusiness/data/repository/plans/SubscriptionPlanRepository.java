package com.singhtwenty2.OceanVistaBusiness.data.repository.plans;

import com.singhtwenty2.OceanVistaBusiness.data.model.entity.SubscriptionPlan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscriptionPlanRepository extends JpaRepository<SubscriptionPlan, Long> {
    SubscriptionPlan findByName(String name);
}
