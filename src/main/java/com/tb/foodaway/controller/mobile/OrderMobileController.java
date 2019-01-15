package com.tb.foodaway.controller.mobile;

import com.tb.foodaway.model.dto.request.PlaceOrderRequest;
import com.tb.foodaway.model.dto.response.OrderResponse;
import com.tb.foodaway.model.dto.response.PlaceOrderResponse;
import com.tb.foodaway.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/mobile/order")
@PreAuthorize("hasRole('MOBILE_USER')")
public class OrderMobileController {

    private final OrderService orderService;

    @PostMapping
    public PlaceOrderResponse placeOrder(@Valid @RequestBody PlaceOrderRequest placeOrderRequest, Principal principal) {
        return orderService.placeOrder(principal.getName(), placeOrderRequest);
    }

    @GetMapping
    public List<OrderResponse> getOrders(Principal principal) {
        return orderService.getOrders(principal.getName());
    }
}