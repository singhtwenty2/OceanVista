package com.singhtwenty2.OceanVista.data.repository;

import com.singhtwenty2.OceanVista.data.model.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthRepository extends JpaRepository<Users, Long> {
    Users findByEmail(String email);
    Users findByUsername(String username);
}
