package com.tb.bimo.configuration;

import com.tb.bimo.repository.*;
import com.tb.bimo.service.*;
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

    @Bean
    public BranchService branchService(BranchRepository branchRepository) {
        return new BranchService(branchRepository);
    }

    @Bean
    public BasketService basketService(BasketRepository basketRepository) {
        return new BasketService(basketRepository);
    }

    @Bean
    public CampaignService campaignService(CampaignRepository campaignRepository) {
        return new CampaignService(campaignRepository);
    }
}
