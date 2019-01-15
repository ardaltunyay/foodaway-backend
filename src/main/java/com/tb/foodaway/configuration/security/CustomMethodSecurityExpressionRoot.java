package com.tb.foodaway.configuration.security;

import com.tb.foodaway.exception.ResourceNotFoundException;
import com.tb.foodaway.model.common.AdminCompanyRole;
import com.tb.foodaway.repository.UserRepository;
import com.tb.foodaway.service.core.UserSecurityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.expression.SecurityExpressionRoot;
import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations;
import org.springframework.security.core.Authentication;

import java.util.List;

@Slf4j
public class CustomMethodSecurityExpressionRoot extends SecurityExpressionRoot implements MethodSecurityExpressionOperations {

    private Object filterObject;
    private Object returnObject;
    private UserSecurityService userSecurityService;
    private final UserRepository userRepository;

    public CustomMethodSecurityExpressionRoot(Authentication authentication, UserSecurityService userSecurityService, UserRepository userRepository) {
        super(authentication);
        this.userSecurityService = userSecurityService;
        this.userRepository = userRepository;
    }

    public boolean isCompanyAuthorized(String companyId) {
        String userId = authentication.getName();
        log.debug("Checking if user is Admin of CompanyId:{}, user:{}", companyId, authentication.getName());

        List<AdminCompanyRole> adminCompanyRoleList = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found.")).getAdminCompanyRoles();

        boolean isAuthorized = false;
        for (AdminCompanyRole adminCompanyRole : adminCompanyRoleList) {
            if (adminCompanyRole.getCompanyId().equals(companyId)) {
                isAuthorized = true;
            }
        }
        return isAuthorized;
    }

    public boolean isBranchAuthorized(String branchId) {
        String userId = authentication.getName();
        log.debug("Checking if user is authorized for BranchId:{}, user:{}", branchId, userId);

        return userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found.")).getAuthorizedBranch().equals(branchId);
    }

    @Override
    public Object getFilterObject() {
        return this.filterObject;
    }

    @Override
    public void setFilterObject(Object obj) {
        this.filterObject = obj;
    }

    @Override
    public Object getReturnObject() {
        return this.returnObject;
    }

    @Override
    public void setReturnObject(Object obj) {
        this.returnObject = obj;
    }

    @Override
    public Object getThis() {
        return this;
    }
}
