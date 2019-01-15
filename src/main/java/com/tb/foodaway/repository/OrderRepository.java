package com.tb.foodaway.repository;

import com.tb.foodaway.model.enums.OrderStatus;
import com.tb.foodaway.model.persistance.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface OrderRepository extends MongoRepository<Order, String> {
    boolean existsByOrderNumber(String orderNumber);
    Optional<Order> findByOrderNumberAndCompanyId(String orderNumber, String companyId);
    Optional<Order> findByOrderNumberAndUserId(String orderNumber, String userId);
    List<Order> findAllByBranchIdAndStatusIn(String branchId, List<OrderStatus> orderStatusList);
    List<Order> findAllByBranchIdAndStatusInAndDateCreatedBetween(String branchId, List<OrderStatus> orderStatusList, Date from, Date to);
    List<Order> findAllByUserId(String userId);
}
