package com.tb.bimo.repository;

import com.tb.bimo.model.persistance.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {
}
