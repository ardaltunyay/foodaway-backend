package com.tb.foodaway.repository;

import com.tb.foodaway.model.persistance.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {
}
