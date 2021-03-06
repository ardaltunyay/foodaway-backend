package com.tb.foodaway.repository;

import com.tb.foodaway.model.persistance.Basket;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface BasketRepository extends MongoRepository<Basket, String> {

    List<Basket> findAllByUserId(String userId);
    Optional<Basket> findByUserIdAndBranchId(String userId, String branchId);
    Long deleteAllByUserId(String userId);
    Long deleteByUserIdAndBranchId(String userId, String branchId);
}
