package com.tb.foodaway.configuration.security;

import com.tb.foodaway.repository.UserRepository;
import com.tb.foodaway.service.core.UserDetailsService;
import com.tb.foodaway.service.core.UserSecurityService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class SecurityServiceConfiguration {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository) {
        return new UserDetailsService(userRepository);
    }

    @Bean
    public UserSecurityService userSecurityService() {
        return new UserSecurityService();
    }
}
