package com.tb.foodaway.model.dto.mapper;

import com.tb.foodaway.model.dto.request.CreateCampaignRequest;
import com.tb.foodaway.model.persistance.Campaign;
import org.mapstruct.Mapper;

@Mapper
public interface CampaignMapper {
    Campaign createCampaignRequestToCampaign(CreateCampaignRequest createCampaignRequest);
}
