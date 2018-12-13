package com.tb.bimo.configuration;

import com.tb.bimo.repository.ProductRepository;
import com.tb.bimo.repository.UserRepository;
import com.tb.bimo.service.ProductService;
import com.tb.bimo.service.UserService;
import com.tb.bimo.service.core.UserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class ServiceConfiguration {

    @Bean
    public UserService userService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        return new UserService(userRepository, bCryptPasswordEncoder);
    }

    @Bean
    public ProductService productService(ProductRepository productRepository) {
        return new ProductService(productRepository);
    }
}
