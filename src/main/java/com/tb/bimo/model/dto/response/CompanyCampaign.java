package com.tb.bimo.model.dto.response;

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
    private List<Campaign> campaignList;
}
