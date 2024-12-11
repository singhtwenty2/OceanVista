package com.singhtwenty2.OceanVistaBusiness.service.plans;

import com.singhtwenty2.OceanVistaBusiness.data.model.entity.SubscriptionPlan;
import com.singhtwenty2.OceanVistaBusiness.data.repository.plans.SubscriptionPlanRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubscriptionPlanService {

    private final SubscriptionPlanRepository repository;
    public SubscriptionPlanService(SubscriptionPlanRepository repository) {
        this.repository = repository;
    }

    public boolean savePlan(SubscriptionPlan subscriptionPlan) {
        try {
            SubscriptionPlan plan = repository.save(subscriptionPlan);
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<SubscriptionPlan> getAllPlans() {
        return repository.findAll();
    }

    public SubscriptionPlan getPlanByName(String name) {
        return repository.findByName(name);
    }

    public SubscriptionPlan getPlanById(Long id) {
        return repository.findById(id).orElse(null);
    }

}
