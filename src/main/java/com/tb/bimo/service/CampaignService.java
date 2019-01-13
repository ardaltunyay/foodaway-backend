package com.tb.bimo.service;

import com.tb.bimo.exception.ResourceNotFoundException;
import com.tb.bimo.model.dto.mapper.CampaignMapper;
import com.tb.bimo.model.dto.request.CreateCampaignRequest;
import com.tb.bimo.model.persistance.Campaign;
import com.tb.bimo.model.persistance.Company;
import com.tb.bimo.repository.CampaignRepository;
import com.tb.bimo.repository.CompanyRepository;
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
