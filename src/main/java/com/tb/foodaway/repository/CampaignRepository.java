package com.tb.foodaway.repository;

import com.tb.foodaway.model.persistance.Campaign;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface CampaignRepository extends MongoRepository<Campaign, String> {

    Optional<Campaign> findByCompanyIdAndId(String companyId, String campaignId);
    List<Campaign> findAllByCompanyId(String companyId);
}
