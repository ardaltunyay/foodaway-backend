package com.tb.foodaway.model.dto.common;

import com.tb.foodaway.model.persistance.Campaign;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CompanyCampaign {

    private String companyName;
    private String branchId;
    private List<Campaign> campaignList;
}
