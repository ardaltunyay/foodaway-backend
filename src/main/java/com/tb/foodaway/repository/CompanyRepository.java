package com.tb.foodaway.repository;

import com.tb.foodaway.model.persistance.Company;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CompanyRepository extends MongoRepository<Company, String> {
}
