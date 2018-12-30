package com.tb.bimo.service;

import com.tb.bimo.exception.ResourceNotFoundException;
import com.tb.bimo.model.persistance.Basket;
import com.tb.bimo.repository.BasketRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class BasketService {

    private final BasketRepository basketRepository;

    public Basket getBasketOfUser(String userId) {
        if (basketRepository.findByUserId(userId).isPresent()) {
            return basketRepository.findByUserId(userId).get();
        } else {
            log.info("Basket not found for userId: {}", userId);
            throw  new ResourceNotFoundException("Basket not found for user.");
        }
    }
}
