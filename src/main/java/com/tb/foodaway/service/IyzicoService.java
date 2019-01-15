package com.tb.foodaway.service;

import com.iyzipay.Options;
import com.iyzipay.model.*;
import com.iyzipay.request.CreatePaymentRequest;
import com.tb.foodaway.configuration.IyzicoConfiguration;
import com.tb.foodaway.model.common.BasketProduct;
import com.tb.foodaway.model.dto.request.PlaceOrderRequest;
import com.tb.foodaway.model.persistance.Basket;
import com.tb.foodaway.model.persistance.Campaign;
import com.tb.foodaway.model.persistance.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

@Slf4j
@Service
public class IyzicoService {

    private final Options options = new Options();

    IyzicoService(IyzicoConfiguration iyzicoConfiguration) {
        this.options.setApiKey(iyzicoConfiguration.getApiKey());
        this.options.setSecretKey(iyzicoConfiguration.getSecretKey());
        this.options.setBaseUrl(iyzicoConfiguration.getBaseUrl());
    }

    Payment createPayment(User user, PlaceOrderRequest placeOrderRequest, Basket basket, String orderNumber) {

        CreatePaymentRequest request = new CreatePaymentRequest();

        Double price = basket.calculatePrice();

        List<BasketItem> basketItems = new ArrayList<>();

        ListIterator litr = basket.getProductList().listIterator();
        while (litr.hasNext()) {
            BasketProduct basketProduct = (BasketProduct) litr.next();

            BasketItem basketItem = new BasketItem();
            basketItem.setId(basketProduct.getProduct().getId());
            basketItem.setName(basketProduct.getProduct().getTitle());
            basketItem.setCategory1(basketProduct.getProduct().getCompanyId());
            basketItem.setItemType(BasketItemType.PHYSICAL.name());
            basketItem.setPrice(new BigDecimal(basketProduct.getProduct().getPrice()));

            for (int i=0; i<basketProduct.getQuantity(); i++) {
                basketItems.add(basketItem);
            }
        }
        request.setBasketItems(basketItems);

        request.setLocale(Locale.TR.getValue());
        request.setConversationId(orderNumber);
        request.setPrice(new BigDecimal(price));
        request.setPaidPrice(new BigDecimal(price));
        request.setCurrency(Currency.TRY.name());
        request.setInstallment(1);
        request.setBasketId(basket.getId());
        request.setPaymentChannel(PaymentChannel.MOBILE.name());
        request.setPaymentGroup(PaymentGroup.PRODUCT.name());

        PaymentCard paymentCard = new PaymentCard();
        paymentCard.setCardHolderName(placeOrderRequest.getCardHolderName());
        paymentCard.setCardNumber(placeOrderRequest.getCardNumber());
        paymentCard.setExpireMonth(placeOrderRequest.getExpireMonth());
        paymentCard.setExpireYear(placeOrderRequest.getExpireYear());
        paymentCard.setCvc(placeOrderRequest.getCvc());
        paymentCard.setRegisterCard(0);
        request.setPaymentCard(paymentCard);

        // DUMMY DATA TO REACH IYZICO TEST SANDBOX
        // REAL USER DATA WILL BE USED IN PRODUCTION ENVIRONMENT
        Buyer buyer = new Buyer();
        buyer.setId(user.getId());
        buyer.setName("John");
        buyer.setSurname("Doe");
        buyer.setGsmNumber("+905350000000");
        buyer.setEmail(user.getEmail());
        buyer.setIdentityNumber("74300864791");
        buyer.setRegistrationAddress("Nidakule Göztepe, Merdivenköy Mah. Bora Sok. No:1");
        buyer.setIp("85.34.78.112");
        buyer.setCity("Istanbul");
        buyer.setCountry("Turkey");
        buyer.setZipCode("34732");
        request.setBuyer(buyer);

        Address shippingAddress = new Address();
        shippingAddress.setContactName("Jane Doe");
        shippingAddress.setCity("Istanbul");
        shippingAddress.setCountry("Turkey");
        shippingAddress.setAddress("Nidakule Göztepe, Merdivenköy Mah. Bora Sok. No:1");
        shippingAddress.setZipCode("34742");
        request.setShippingAddress(shippingAddress);

        Address billingAddress = new Address();
        billingAddress.setContactName("Jane Doe");
        billingAddress.setCity("Istanbul");
        billingAddress.setCountry("Turkey");
        billingAddress.setAddress("Nidakule Göztepe, Merdivenköy Mah. Bora Sok. No:1");
        billingAddress.setZipCode("34742");
        request.setBillingAddress(billingAddress);

        return Payment.create(request, options);
    }

    Payment createPayment(User user, PlaceOrderRequest placeOrderRequest, Basket basket, Campaign campaign, String orderNumber) {

        CreatePaymentRequest request = new CreatePaymentRequest();

        BigDecimal price = new BigDecimal(0);
        Double paidPrice = basket.calculatePrice() - basket.calculatePrice() * campaign.getDiscountRate();

        List<BasketItem> basketItems = new ArrayList<>();

        ListIterator litr = basket.getProductList().listIterator();
        while (litr.hasNext()) {
            BasketProduct basketProduct = (BasketProduct) litr.next();

            BasketItem basketItem = new BasketItem();
            basketItem.setId(basketProduct.getProduct().getId());
            basketItem.setName(basketProduct.getProduct().getTitle());
            basketItem.setCategory1(basketProduct.getProduct().getCompanyId());
            basketItem.setItemType(BasketItemType.PHYSICAL.name());
            basketItem.setPrice(new BigDecimal(basketProduct.getProduct().getPrice()));

            for (int i=0; i<basketProduct.getQuantity(); i++) {
                price = price.add(basketItem.getPrice());
                basketItems.add(basketItem);
            }
        }
        request.setBasketItems(basketItems);

        request.setLocale(Locale.TR.getValue());
        request.setConversationId(orderNumber);
        request.setPrice(price);
        request.setPaidPrice(new BigDecimal(paidPrice));
        request.setCurrency(Currency.TRY.name());
        request.setInstallment(1);
        request.setBasketId(basket.getId());
        request.setPaymentChannel(PaymentChannel.MOBILE.name());
        request.setPaymentGroup(PaymentGroup.PRODUCT.name());

        PaymentCard paymentCard = new PaymentCard();
        paymentCard.setCardHolderName(placeOrderRequest.getCardHolderName());
        paymentCard.setCardNumber(placeOrderRequest.getCardNumber());
        paymentCard.setExpireMonth(placeOrderRequest.getExpireMonth());
        paymentCard.setExpireYear(placeOrderRequest.getExpireYear());
        paymentCard.setCvc(placeOrderRequest.getCvc());
        paymentCard.setRegisterCard(0);
        request.setPaymentCard(paymentCard);

        // DUMMY DATA TO REACH IYZICO TEST SANDBOX
        // REAL USER DATA WILL BE USED IN PRODUCTION ENVIRONMENT
        Buyer buyer = new Buyer();
        buyer.setId(user.getId());
        buyer.setName("John");
        buyer.setSurname("Doe");
        buyer.setGsmNumber("+905350000000");
        buyer.setEmail(user.getEmail());
        buyer.setIdentityNumber("74300864791");
        buyer.setRegistrationAddress("Nidakule Göztepe, Merdivenköy Mah. Bora Sok. No:1");
        buyer.setIp("85.34.78.112");
        buyer.setCity("Istanbul");
        buyer.setCountry("Turkey");
        buyer.setZipCode("34732");
        request.setBuyer(buyer);

        Address shippingAddress = new Address();
        shippingAddress.setContactName("Jane Doe");
        shippingAddress.setCity("Istanbul");
        shippingAddress.setCountry("Turkey");
        shippingAddress.setAddress("Nidakule Göztepe, Merdivenköy Mah. Bora Sok. No:1");
        shippingAddress.setZipCode("34742");
        request.setShippingAddress(shippingAddress);

        Address billingAddress = new Address();
        billingAddress.setContactName("Jane Doe");
        billingAddress.setCity("Istanbul");
        billingAddress.setCountry("Turkey");
        billingAddress.setAddress("Nidakule Göztepe, Merdivenköy Mah. Bora Sok. No:1");
        billingAddress.setZipCode("34742");
        request.setBillingAddress(billingAddress);

        return Payment.create(request, options);
    }
}
