package com.tb.bimo.repository;

import com.tb.bimo.model.persistance.Basket;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface BasketRepository extends MongoRepository<Basket, String> {

    List<Basket> findAllByUserId(String userId);
    Optional<Basket> findByUserIdAndBranchId(String userId, String branchId);
    boolean deleteAllByUserId(String userId);
    boolean deleteByUserIdAndAndBranchId(String userId, String branchId);
}
