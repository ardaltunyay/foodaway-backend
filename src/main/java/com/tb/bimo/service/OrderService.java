package com.tb.bimo.service;

import com.iyzipay.model.Payment;
import com.tb.bimo.exception.ResourceNotFoundException;
import com.tb.bimo.model.dto.request.PlaceOrderRequest;
import com.tb.bimo.model.dto.response.PlaceOrderResponse;
import com.tb.bimo.model.persistance.Basket;
import com.tb.bimo.model.persistance.Campaign;
import com.tb.bimo.model.persistance.Order;
import com.tb.bimo.model.persistance.User;
import com.tb.bimo.repository.BasketRepository;
import com.tb.bimo.repository.CampaignRepository;
import com.tb.bimo.repository.OrderRepository;
import com.tb.bimo.repository.UserRepository;
import com.tb.bimo.util.TokenGenerator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final BasketRepository basketRepository;
    private final CampaignRepository campaignRepository;
    private final UserRepository userRepository;

    @Autowired
    private IyzicoService iyzicoService;

    public PlaceOrderResponse placeOrder(String userId, PlaceOrderRequest placeOrderRequest) {
        Basket basket = basketRepository.findById(placeOrderRequest.getBasketId()).orElseThrow(() -> new ResourceNotFoundException("Basket not found."));

        Optional<Campaign> optionalCampaign = Optional.empty();
        if (basket.getCampaignId() != null) {
            optionalCampaign = campaignRepository.findById(basket.getCampaignId());
        }

        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found."));

        String orderNumber;
        do {
            orderNumber = TokenGenerator.generateToken();
        } while (orderRepository.existsByOrderNumber(orderNumber));

        Payment payment;
        if (optionalCampaign != null && optionalCampaign.isPresent()) {
            payment = iyzicoService.createPayment(user, placeOrderRequest, basket, optionalCampaign.get(), orderNumber);
        } else {
            payment = iyzicoService.createPayment(user, placeOrderRequest, basket, orderNumber);
        }

        Order order = Order
                .builder()
                .orderNumber(orderNumber)
                .campaignId(basket.getCampaignId())
                .companyId(basket.getCompanyId())
                .companyName(basket.getCompanyName())
                .branchId(basket.getBranchId())
                .productList(basket.getProductList())
                .totalPrice(payment.getPaidPrice().doubleValue())
                .userId(userId)
                .status(payment.getStatus())
                .paymentId(payment.getPaymentId())
                .build();

        orderRepository.save(order);
        basketRepository.deleteById(basket.getId());

        // TODO throw a new event of sending notification to the restaurant screen
        return PlaceOrderResponse.builder().orderNumber(orderNumber).build();
    }

    public List<Order> getActiveOrdersByBranchId(String branchId) {
        return orderRepository.findAllByBranchIdAndStatus(branchId, "success");
    }

    public void completeOrder(String orderId) {
        orderRepository.findById(orderId).orElseThrow(() -> new ResourceNotFoundException("Order not found."))
                .setStatus("completed");
    }

    public void cancelOrder(String orderId) {
        orderRepository.findById(orderId).orElseThrow(() -> new ResourceNotFoundException("Order not found."))
                .setStatus("canceled-by-restaurant");
    }
}
