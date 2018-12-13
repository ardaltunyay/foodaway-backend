package com.tb.bimo.configuration.security;

import com.tb.bimo.repository.UserRepository;
import com.tb.bimo.service.core.UserDetailsService;
import com.tb.bimo.service.core.UserSecurityService;
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
