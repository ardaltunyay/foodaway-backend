package com.tb.bimo.controller.mobile;

import com.tb.bimo.model.dto.request.FindByLocationRequest;
import com.tb.bimo.model.dto.response.CompanyCampaign;
import com.tb.bimo.model.persistance.Branch;
import com.tb.bimo.model.persistance.Campaign;
import com.tb.bimo.service.BranchService;
import com.tb.bimo.service.CampaignService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/mobile/campaign")
public class CampaignMobileController {

    private final CampaignService campaignService;
    private final BranchService branchService;

    @GetMapping
    public Set<CompanyCampaign> getCampaignListByLocationNear(@RequestBody FindByLocationRequest findByLocationRequest) {
        List<Branch> nearBranches = branchService.getBranchListByLocationNear(
                findByLocationRequest.getLatitude(),
                findByLocationRequest.getLongitude(),
                findByLocationRequest.getDistance()
        );

        Set<CompanyCampaign> companyCampaignList = new HashSet<>();

        nearBranches.forEach(branch -> {
            List<Campaign> campaignList = campaignService.getCampaignListByCompany(branch.getCompanyId());
            if (campaignList.iterator().hasNext()) {
                companyCampaignList.add(
                        CompanyCampaign
                            .builder()
                            .companyName(campaignList.iterator().next().getCompanyName())
                            .branchId(branch.getId())
                            .campaignList(campaignList)
                            .build()
                );
            }
        });

        return companyCampaignList;
    }
}
