package com.tb.bimo.repository;

import com.tb.bimo.model.persistance.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends MongoRepository<Order, String> {
    boolean existsByOrderNumber(String orderNumber);
    Optional<Order> findByOrderNumberAndCompanyId(String orderNumber, String companyId);
    Optional<Order> findByOrderNumberAndUserId(String orderNumber, String userId);
    List<Order> findAllByBranchIdAndStatus(String branchId, String status);
}
