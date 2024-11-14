package com.singhtwenty2.OceanVista.data.repository;

import com.singhtwenty2.OceanVista.data.model.entity.UserLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface LocationRepository extends JpaRepository<UserLocation, UUID> {
    UserLocation findAllByUserId(Long userId);
}
