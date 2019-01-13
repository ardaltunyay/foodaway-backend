package com.tb.bimo.controller.mobile;

import com.tb.bimo.exception.ResourceNotFoundException;
import com.tb.bimo.model.dto.common.CompanyCampaign;
import com.tb.bimo.model.dto.response.CampaignListByLocationResponse;
import com.tb.bimo.model.persistance.Branch;
import com.tb.bimo.model.persistance.Campaign;
import com.tb.bimo.service.BranchService;
import com.tb.bimo.service.CampaignService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/mobile/campaign")
public class CampaignMobileController {

    private final CampaignService campaignService;
    private final BranchService branchService;

    @GetMapping
    public CampaignListByLocationResponse getCampaignListByLocationNear(@RequestParam String latitude, @RequestParam String longitude, @RequestParam String distance) {
        List<Branch> nearBranches = branchService.getBranchListByLocationNear(
                latitude,
                longitude,
                Double.valueOf(distance)
        );

        Set<CompanyCampaign> companyCampaignList = new HashSet<>();

        nearBranches.forEach(branch -> {
            List<Campaign> campaignList = campaignService.getCampaignListByCompany(branch.getCompanyId());

            ListIterator litr = campaignList.listIterator();
            if (litr.hasNext()) {
                companyCampaignList.add(
                        CompanyCampaign
                            .builder()
                            .companyName(((Campaign) litr.next()).getCompanyName())
                            .branchId(branch.getId())
                            .campaignList(campaignList)
                            .build()
                );
            }
        });

        if (companyCampaignList.isEmpty()) {
            throw new ResourceNotFoundException("No campaign found near given location.");
        } else {
            return CampaignListByLocationResponse.builder().campaignList(companyCampaignList).build();
        }
    }
}
