package com.singhtwenty2.OceanVista.service.auth;

import com.singhtwenty2.OceanVista.config.PrincipalUserDetailsImpl;
import com.singhtwenty2.OceanVista.data.model.entity.Users;
import com.singhtwenty2.OceanVista.data.repository.AuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class PrincipalUserDetailsService implements UserDetailsService {

    @Autowired
    private AuthRepository repository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Users user = repository.findByEmail(email);
        if (user == null) {
            System.out.println("User Not Found...");
            throw new UsernameNotFoundException("User Not Found 404");
        }
        return new PrincipalUserDetailsImpl(user);
    }
}
