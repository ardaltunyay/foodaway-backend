package com.tb.bimo.service;

import com.tb.bimo.model.persistance.Campaign;
import com.tb.bimo.repository.CampaignRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class CampaignService {

    private final CampaignRepository campaignRepository;

    public List<Campaign> getCampaignListByCompany(String companyId) {
        return campaignRepository.findAllByCompanyId(companyId);
    }
}
