package com.tb.foodaway.service;

import com.tb.foodaway.exception.ResourceNotFoundException;
import com.tb.foodaway.model.dto.mapper.CampaignMapper;
import com.tb.foodaway.model.dto.request.CreateCampaignRequest;
import com.tb.foodaway.model.persistance.Campaign;
import com.tb.foodaway.model.persistance.Company;
import com.tb.foodaway.repository.CampaignRepository;
import com.tb.foodaway.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class CampaignService {

    private final CampaignRepository campaignRepository;
    private final CompanyRepository companyRepository;
    private CampaignMapper campaignMapper = Mappers.getMapper(CampaignMapper.class);

    public List<Campaign> getCampaignListByCompany(String companyId) {
        return campaignRepository.findAllByCompanyId(companyId);
    }

    public void addCampaign(CreateCampaignRequest createCampaignRequest, String companyId) {
        Campaign campaign = campaignMapper.createCampaignRequestToCampaign(createCampaignRequest);
        Company company = companyRepository.findById(companyId).orElseThrow(() -> new ResourceNotFoundException("Company not found."));

        campaign.setCompanyId(company.getId());
        campaign.setCompanyName(company.getTitle());

        campaignRepository.save(campaign);
    }
}
