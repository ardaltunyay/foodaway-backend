package com.tb.bimo.repository;

import com.tb.bimo.model.persistance.Company;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CompanyRepository extends MongoRepository<Company, String> {
}
