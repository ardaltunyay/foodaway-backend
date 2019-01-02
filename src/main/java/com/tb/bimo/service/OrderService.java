package com.tb.bimo.service;

import com.tb.bimo.exception.ResourceNotFoundException;
import com.tb.bimo.model.common.BasketProduct;
import com.tb.bimo.model.dto.response.PlaceOrderResponse;
import com.tb.bimo.model.persistance.Basket;
import com.tb.bimo.model.persistance.Order;
import com.tb.bimo.repository.BasketRepository;
import com.tb.bimo.repository.OrderRepository;
import com.tb.bimo.util.TokenGenerator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final BasketRepository basketRepository;

    public PlaceOrderResponse placeOrder(String userId, String basketId) {
        Basket basket = basketRepository.findById(basketId).orElseThrow(() -> new ResourceNotFoundException("Basket not found."));

        String orderNumber;
        do {
            orderNumber = TokenGenerator.generateToken();
        } while (orderRepository.existsByOrderNumber(orderNumber));

        Double totalPrice = 0.00;
        while (basket.getProductList().iterator().hasNext()) {
            BasketProduct basketProduct = basket.getProductList().iterator().next();
            totalPrice += basketProduct.getQuantity() * basketProduct.getProduct().getPrice();
        }

        Order order = Order
                .builder()
                .orderNumber(orderNumber)
                .campaignId(basket.getCampaignId())
                .companyId(basket.getCompanyId())
                .companyName(basket.getCompanyName())
                .productList(basket.getProductList())
                .totalPrice(totalPrice)
                .userId(userId)
                .build();

        orderRepository.save(order);

        // TODO throw a new event of sending notification to the restaurant screen
        return PlaceOrderResponse.builder().orderNumber(orderNumber).build();
    }
}
