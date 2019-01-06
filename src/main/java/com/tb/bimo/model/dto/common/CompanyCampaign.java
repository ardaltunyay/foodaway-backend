package com.tb.bimo.model.dto.common;

import com.tb.bimo.model.persistance.Campaign;
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
