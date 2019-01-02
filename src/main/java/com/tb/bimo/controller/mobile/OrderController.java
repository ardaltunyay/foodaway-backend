package com.tb.bimo.controller.mobile;

import com.tb.bimo.model.dto.response.PlaceOrderResponse;
import com.tb.bimo.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/mobile/order")
@PreAuthorize("hasRole('MOBILE_USER')")
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public PlaceOrderResponse placeOrder(@RequestParam String basketId, Principal principal) {
        return orderService.placeOrder(principal.getName(), basketId);
    }
}
