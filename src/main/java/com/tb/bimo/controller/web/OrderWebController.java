package com.tb.bimo.controller.web;

import com.tb.bimo.model.persistance.Order;
import com.tb.bimo.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/web/branch/{branchId}/order")
public class OrderWebController {

    private final OrderService orderService;

    @GetMapping
    @PreAuthorize("hasRole('ROLE_RESTAURANT_USER') and isBranchAuthorized(#branchId)")
    public List<Order> getOrders(@PathVariable String branchId, @RequestParam String status) {
        if (status.equals("active")) {
            return orderService.getActiveOrdersByBranchId(branchId);
        } else if (status.equals("inactive")) {
            return orderService.getInactiveOrdersByBranchId(branchId);
        } else {
            return null;
        }
    }

    @PutMapping("/{orderId}/complete")
    @PreAuthorize("hasRole('ROLE_RESTAURANT_USER') and isBranchAuthorized(#branchId)")
    public void completeOrder(@PathVariable String branchId, @PathVariable String orderId) {
        orderService.completeOrder(orderId);
    }

    @PutMapping("/{orderId}/cancel")
    @PreAuthorize("hasRole('ROLE_RESTAURANT_USER') and isBranchAuthorized(#branchId)")
    public void cancelOrder(@PathVariable String branchId, @PathVariable String orderId) {
        orderService.cancelOrder(orderId);
    }

    @PutMapping("/{orderId}/set-timer")
    @PreAuthorize("hasRole('ROLE_RESTAURANT_USER') and isBranchAuthorized(#branchId)")
    public void setTimer(@PathVariable String branchId, @PathVariable String orderId, @RequestParam String time) {
        orderService.setTimer(orderId, time);
    }

    @PutMapping("/{orderId}/revert")
    @PreAuthorize("hasRole('ROLE_RESTAURANT_USER') and isBranchAuthorized(#branchId)")
    public void revertOrder(@PathVariable String branchId, @PathVariable String orderId) {
        orderService.revertOrder(orderId);
    }
}
