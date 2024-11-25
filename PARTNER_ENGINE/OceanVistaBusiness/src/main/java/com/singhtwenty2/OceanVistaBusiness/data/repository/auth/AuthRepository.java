package com.singhtwenty2.OceanVistaBusiness.data.repository.auth;

import com.singhtwenty2.OceanVistaBusiness.data.model.entity.Partner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthRepository extends JpaRepository<Partner, Long> {
    Partner findByEmail(String email);
}
