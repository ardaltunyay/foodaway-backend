package com.tb.bimo.service;

import com.iyzipay.model.Currency;
import com.iyzipay.model.Locale;
import com.iyzipay.model.PaymentChannel;
import com.iyzipay.model.PaymentGroup;
import com.iyzipay.request.CreatePaymentRequest;
import com.tb.bimo.exception.ResourceNotFoundException;
import com.tb.bimo.model.common.BasketProduct;
import com.tb.bimo.model.dto.request.PlaceOrderRequest;
import com.tb.bimo.model.dto.response.PlaceOrderResponse;
import com.tb.bimo.model.persistance.Basket;
import com.tb.bimo.model.persistance.Campaign;
import com.tb.bimo.model.persistance.Order;
import com.tb.bimo.repository.BasketRepository;
import com.tb.bimo.repository.CampaignRepository;
import com.tb.bimo.repository.OrderRepository;
import com.tb.bimo.util.TokenGenerator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final BasketRepository basketRepository;
    private final CampaignRepository campaignRepository;

    public PlaceOrderResponse placeOrder(String userId, PlaceOrderRequest placeOrderRequest) {
        Basket basket = basketRepository.findById(placeOrderRequest.getBasketId()).orElseThrow(() -> new ResourceNotFoundException("Basket not found."));
        Optional<Campaign> campaign = campaignRepository.findById(basket.getCampaignId());
        Double price = basket.calculatePrice();
        Double paidPrice = price;

        if (campaign.isPresent()) {
            paidPrice = price - price * campaign.get().getDiscountRate();
        }

        CreatePaymentRequest request = new CreatePaymentRequest();
        request.setLocale(Locale.TR.getValue());
        request.setConversationId("123456789");
        request.setPrice(new BigDecimal(price));
        request.setPaidPrice(new BigDecimal(paidPrice));
        request.setCurrency(Currency.TRY.name());
        request.setInstallment(1);
        request.setBasketId("B67832");
        request.setPaymentChannel(PaymentChannel.MOBILE.name());
        request.setPaymentGroup(PaymentGroup.PRODUCT.name());




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
