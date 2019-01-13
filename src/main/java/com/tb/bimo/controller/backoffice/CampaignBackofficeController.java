package com.tb.bimo.controller.backoffice;

import com.tb.bimo.model.dto.request.CreateCampaignRequest;
import com.tb.bimo.service.CampaignService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/backoffice/company/{companyId}/campaign")
public class CampaignBackofficeController {

    private final CampaignService campaignService;

    @PostMapping
    @PreAuthorize("hasRole('ROLE_CONTENT_MANAGER') and isCompanyAuthorized(#companyId)")
    public void addCampaign(@RequestBody CreateCampaignRequest createCampaignRequest, @PathVariable String companyId) {
        campaignService.addCampaign(createCampaignRequest, companyId);
    }
}
