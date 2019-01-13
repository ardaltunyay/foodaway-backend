package com.tb.bimo.model.dto.mapper;

import com.tb.bimo.model.dto.request.CreateCampaignRequest;
import com.tb.bimo.model.persistance.Campaign;
import org.mapstruct.Mapper;

@Mapper
public interface CampaignMapper {
    Campaign createCampaignRequestToCampaign(CreateCampaignRequest createCampaignRequest);
}
