package com.tb.foodaway.repository;

import com.tb.foodaway.model.persistance.Menu;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface MenuRepository extends MongoRepository<Menu, String> {
    Optional<Menu> findByBranchId(String branchId);
    Optional<Menu> findByCompanyId(String companyId);
}
