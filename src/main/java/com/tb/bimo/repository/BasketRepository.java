package com.tb.bimo.repository;

import com.tb.bimo.model.persistance.Basket;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface BasketRepository extends MongoRepository<Basket, String> {

    Optional<Basket> findByUserId(String userId);
}
