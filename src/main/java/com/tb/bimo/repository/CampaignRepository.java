package com.tb.bimo.repository;

import com.tb.bimo.model.persistance.Campaign;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CampaignRepository extends MongoRepository<Campaign, String> {

    List<Campaign> findAllByCompanyId(String companyId);
}
