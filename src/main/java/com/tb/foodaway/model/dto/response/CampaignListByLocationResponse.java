package com.tb.foodaway.model.dto.response;

import com.tb.foodaway.model.dto.common.CompanyCampaign;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CampaignListByLocationResponse {

    Set<CompanyCampaign> campaignList;
}
