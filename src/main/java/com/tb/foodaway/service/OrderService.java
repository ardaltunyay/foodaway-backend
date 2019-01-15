package com.tb.foodaway.service;

import com.iyzipay.model.Payment;
import com.tb.foodaway.exception.BadRequestException;
import com.tb.foodaway.exception.ResourceNotFoundException;
import com.tb.foodaway.model.dto.request.PlaceOrderRequest;
import com.tb.foodaway.model.dto.response.OrderResponse;
import com.tb.foodaway.model.dto.response.PlaceOrderResponse;
import com.tb.foodaway.model.enums.OrderStatus;
import com.tb.foodaway.model.persistance.Basket;
import com.tb.foodaway.model.persistance.Campaign;
import com.tb.foodaway.model.persistance.Order;
import com.tb.foodaway.model.persistance.User;
import com.tb.foodaway.repository.BasketRepository;
import com.tb.foodaway.repository.CampaignRepository;
import com.tb.foodaway.repository.OrderRepository;
import com.tb.foodaway.repository.UserRepository;
import com.tb.foodaway.util.TokenGenerator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;

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
            optionalCampaign = campaignRepository.findByCompanyIdAndId(basket.getCompanyId(), basket.getCampaignId());
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

        if (payment.getStatus().equals("failure")) {
            Order order = Order
                    .builder()
                    .orderNumber(orderNumber)
                    .campaignId(basket.getCampaignId())
                    .companyId(basket.getCompanyId())
                    .companyName(basket.getCompanyName())
                    .branchId(basket.getBranchId())
                    .productList(basket.getProductList())
                    .userId(userId)
                    .paymentStatus(payment.getStatus())
                    .status(payment.getStatus().equals("success") ? OrderStatus.PAYMENT_SUCCESS : OrderStatus.PAYMENT_FAILED)
                    .build();
            orderRepository.save(order);

            throw new BadRequestException(payment.getErrorMessage());
        } else if (payment.getStatus().equals("success")) {
            Order order = Order
                    .builder()
                    .orderNumber(orderNumber)
                    .campaignId(basket.getCampaignId())
                    .companyId(basket.getCompanyId())
                    .companyName(basket.getCompanyName())
                    .branchId(basket.getBranchId())
                    .productList(basket.getProductList())
                    .paidPrice(payment.getPaidPrice().doubleValue())
                    .userId(userId)
                    .paymentStatus(payment.getStatus())
                    .status(payment.getStatus().equals("success") ? OrderStatus.PAYMENT_SUCCESS : OrderStatus.PAYMENT_FAILED)
                    .paymentId(payment.getPaymentId())
                    .build();

            orderRepository.save(order);
            basketRepository.deleteById(basket.getId());

            return PlaceOrderResponse.builder().orderNumber(orderNumber).build();
        } else {
            throw new BadRequestException(payment.getErrorMessage());
        }
    }

    public List<Order> getActiveOrdersByBranchId(String branchId) {
        ZonedDateTime today = ZonedDateTime.of(LocalDate.now(), LocalTime.MIDNIGHT, ZoneId.systemDefault());
        ZonedDateTime tomorrow = today.plusDays(1);
        return orderRepository.findAllByBranchIdAndStatusInAndDateCreatedBetween(branchId, Arrays.asList(OrderStatus.PAYMENT_SUCCESS, OrderStatus.PREPARING), Date.from(today.toInstant()), Date.from(tomorrow.toInstant()));
    }

    public void completeOrder(String orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new ResourceNotFoundException("Order not found."));
        order.setStatus(OrderStatus.READY);
        orderRepository.save(order);
    }

    public void cancelOrder(String orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new ResourceNotFoundException("Order not found."));
        order.setStatus(OrderStatus.CANCELED_BY_BRANCH);
        orderRepository.save(order);
    }

    public void setTimer(String orderId, String time) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new ResourceNotFoundException("Order not found."));
        order.setStatus(OrderStatus.PREPARING);
        order.setPreparingTime(Double.valueOf(time));
        orderRepository.save(order);
    }

    public List<Order> getInactiveOrdersByBranchId(String branchId) {
        ZonedDateTime today = ZonedDateTime.of(LocalDate.now(), LocalTime.MIDNIGHT, ZoneId.systemDefault());
        ZonedDateTime tomorrow = today.plusDays(1);
        return orderRepository.findAllByBranchIdAndStatusInAndDateCreatedBetween(branchId, Arrays.asList(OrderStatus.READY, OrderStatus.CANCELED_BY_BRANCH, OrderStatus.CANCELED_BY_USER, OrderStatus.CANCEL_REQUESTED), Date.from(today.toInstant()), Date.from(tomorrow.toInstant()));
    }

    public void revertOrder(String orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new ResourceNotFoundException("Order not found."));
        order.setStatus(OrderStatus.PREPARING);
        order.setPreparingTime(null);
        orderRepository.save(order);
    }

    public List<OrderResponse> getOrders(String userId) {
        List<Order> orderList = orderRepository.findAllByUserId(userId);
        List<OrderResponse> orderResponseList = new ArrayList<>();

        for (Order order : orderList) {
            orderResponseList.add(
                OrderResponse.builder()
                        .companyName(order.getCompanyName())
                        .dateCreated(order.getDateCreated().toString("d MMMM yyyy HH:mm", new Locale("tr", "TR")))
                        .dateReady(order.getStatus() == OrderStatus.PREPARING ?
                                order.getDateModified().plusMinutes(order.getPreparingTime().intValue()).toString("HH:mm", new Locale("tr", "TR"))
                                : null)
                        .orderNumber(order.getOrderNumber())
                        .paidPrice(order.getPaidPrice())
                        .productList(order.getProductList())
                        .status(order.getStatus())
                        .build()
            );
        }
        Collections.reverse(orderResponseList);
        return orderResponseList;
    }
}
