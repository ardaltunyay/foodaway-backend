package com.tb.bimo.configuration.security;

import com.tb.bimo.repository.UserRepository;
import com.tb.bimo.service.core.UserSecurityService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
@Configuration
@RequiredArgsConstructor
public class MethodSecurityConfig extends GlobalMethodSecurityConfiguration {

    private final UserSecurityService userSecurityService;
    private final UserRepository userRepository;

    @Override
    protected MethodSecurityExpressionHandler createExpressionHandler() {
        CustomMethodSecurityExpressionHandler expressionHandler =
                new CustomMethodSecurityExpressionHandler(userSecurityService, userRepository);
        expressionHandler.setPermissionEvaluator(new CustomPermissionEvaluator());
        return expressionHandler;
    }
}
