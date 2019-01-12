package com.tb.bimo.configuration.security;

import com.tb.bimo.exception.ResourceNotFoundException;
import com.tb.bimo.model.common.AdminCompanyRole;
import com.tb.bimo.repository.UserRepository;
import com.tb.bimo.service.core.UserSecurityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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

    // TODO add controls.
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

    // TODO add controls.
    public boolean isBranchAuthorized(String branchId) {
        String userId = authentication.getName();
        log.debug("Checking if user is authorized for BranchId:{}, user:{}", branchId, userId);

        List<AdminCompanyRole> adminCompanyRoleList = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found.")).getAuthorizedBranches();

        boolean isAuthorized = false;
        for (AdminCompanyRole adminCompanyRole : adminCompanyRoleList) {
            if (adminCompanyRole.getBranchId().equals(branchId)) {
                isAuthorized = true;
            }
        }
        return isAuthorized;
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
