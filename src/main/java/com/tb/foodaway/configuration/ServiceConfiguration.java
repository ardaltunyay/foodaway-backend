package com.tb.foodaway.configuration;

import com.tb.foodaway.repository.*;
import com.tb.foodaway.service.*;
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
    public BasketService basketService(BasketRepository basketRepository, ProductRepository productRepository, CompanyRepository companyRepository, BranchRepository branchRepository, CampaignRepository campaignRepository) {
        return new BasketService(basketRepository, productRepository, companyRepository, branchRepository, campaignRepository);
    }

    @Bean
    public CampaignService campaignService(CampaignRepository campaignRepository, CompanyRepository companyRepository) {
        return new CampaignService(campaignRepository, companyRepository);
    }

    @Bean
    public OrderService orderService(OrderRepository orderRepository, BasketRepository basketRepository, CampaignRepository campaignRepository, UserRepository userRepository) {
        return new OrderService(orderRepository, basketRepository, campaignRepository, userRepository);
    }

    @Bean
    public MenuService menuService(MenuRepository menuRepository, ProductRepository productRepository, CompanyRepository companyRepository, BranchRepository branchRepository) {
        return new MenuService(menuRepository, productRepository, companyRepository, branchRepository);
    }
}
