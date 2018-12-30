package com.tb.bimo.controller.mobile;

import com.tb.bimo.model.persistance.Basket;
import com.tb.bimo.service.BasketService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/mobile/basket")
public class BasketController {

    private final BasketService basketService;

    @GetMapping
    @PreAuthorize("hasRole('MOBILE_USER')")
    public Basket getBasketOfUser(Principal principal) {
        return basketService.getBasketOfUser(principal.getName());
    }

    @PostMapping
    @PreAuthorize("hasRole('MOBILE_USER')")
    public void createBasket(Principal principal) {

    }

    @PutMapping
    @PreAuthorize("hasRole('MOBILE_USER')")
    public void updateBasket(Principal principal) {

    }
}
