package com.tb.bimo.model.dto.response;

import com.tb.bimo.model.dto.common.CompanyCampaign;
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
