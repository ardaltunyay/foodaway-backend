package com.tb.foodaway.controller.backoffice;

import com.tb.foodaway.model.dto.request.CreateBranchRequest;
import com.tb.foodaway.service.BranchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/backoffice/company/{companyId}/branch")
public class BranchBackofficeController {

    private final BranchService branchService;

    @PostMapping
    @PreAuthorize("hasRole('ROLE_CONTENT_MANAGER') and isCompanyAuthorized(#companyId)")
    public void addBranch(@RequestBody CreateBranchRequest createBranchRequest, @PathVariable String companyId) {
        log.info("Adding branch for company: {}", companyId);
        branchService.create(createBranchRequest, companyId);
    }
}
