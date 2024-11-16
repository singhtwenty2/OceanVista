package com.singhtwenty2.OceanVista.data.repository;

import com.singhtwenty2.OceanVista.data.model.entity.Beach;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BeachRepository extends JpaRepository<Beach, Long> {
    List<Beach> findByNameContainingIgnoreCase(String query);
    List<Beach> findByRegionContainingIgnoreCase(String query);
}
