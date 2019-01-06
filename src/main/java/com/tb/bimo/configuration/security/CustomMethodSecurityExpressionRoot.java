package com.tb.bimo.configuration.security;

import com.tb.bimo.service.core.UserSecurityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.expression.SecurityExpressionRoot;
import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations;
import org.springframework.security.core.Authentication;

@Slf4j
public class CustomMethodSecurityExpressionRoot extends SecurityExpressionRoot implements MethodSecurityExpressionOperations {

    private Object filterObject;
    private Object returnObject;
    private UserSecurityService userSecurityService;

    public CustomMethodSecurityExpressionRoot(Authentication authentication, UserSecurityService userSecurityService) {
        super(authentication);
        this.userSecurityService = userSecurityService;
    }

    // TODO add controls.
    public boolean isCompanyAuthorized(String companyId) {
        log.debug("Checking if user is Admin of CompanyId:{}, user:{}", companyId, authentication.getName());
        return true;
    }

    // TODO add controls.
    public boolean isBranchAuthorized(String branchId) {
        log.debug("Checking if user is authorized for BranchId:{}, user:{}", branchId, authentication.getName());
        return true;
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
