package com.singhtwenty2.OceanVistaBusiness.service.auth;

import com.singhtwenty2.OceanVistaBusiness.config.PrincipalUserDetailsImpl;
import com.singhtwenty2.OceanVistaBusiness.data.model.entity.Partner;
import com.singhtwenty2.OceanVistaBusiness.data.repository.auth.AuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class PrincipalUserDetailsService implements UserDetailsService {

    private final AuthRepository repository;

    public PrincipalUserDetailsService(AuthRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Partner partner = repository.findByEmail(email);
        if (partner == null) {
            System.out.println("User Not Found...");
            throw new UsernameNotFoundException("User Not Found 404");
        }
        return new PrincipalUserDetailsImpl(partner);
    }
}
