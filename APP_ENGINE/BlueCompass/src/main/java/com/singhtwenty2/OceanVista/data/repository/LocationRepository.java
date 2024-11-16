package com.singhtwenty2.OceanVista.data.repository;

import com.singhtwenty2.OceanVista.data.model.entity.UserLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends JpaRepository<UserLocation, Long> {
    UserLocation findAllByUserId(Long userId);
}
