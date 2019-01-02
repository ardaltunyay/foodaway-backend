package com.tb.bimo.controller.mobile;

import com.tb.bimo.model.dto.request.CreateBasketRequest;
import com.tb.bimo.model.dto.request.UpdateBasketRequest;
import com.tb.bimo.model.persistance.Basket;
import com.tb.bimo.service.BasketService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/mobile/basket")
@PreAuthorize("hasRole('MOBILE_USER')")
public class BasketController {

    private final BasketService basketService;

    @GetMapping
    public Basket getBasketOfUserByCampaignId(@RequestParam String campaignId, Principal principal) {
        return basketService.getBasketOfUserByCampaignId(principal.getName(), campaignId);
    }

    @GetMapping("/all")
    public List<Basket> getAllBasketsOfUser(Principal principal) {
        return basketService.getAllBasketsOfUser(principal.getName());
    }

    @PostMapping
    public Basket createBasket(@RequestBody CreateBasketRequest createBasketRequest, Principal principal) {
        return basketService.createBasket(principal.getName(), createBasketRequest);
    }

    @PutMapping
    public Basket updateBasket(@RequestBody UpdateBasketRequest updateBasketRequest, Principal principal) {
        return basketService.updateBasket(principal.getName(), updateBasketRequest);
    }

    @DeleteMapping
    public void deleteBasketOfUserByCampaignId(@RequestParam String campaignId, Principal principal) {
        basketService.deleteBasketOfUserByCampaignId(principal.getName(), campaignId);
    }

    @DeleteMapping("/all")
    public void deleteAllBasketsOfUser(Principal principal) {
        basketService.deleteAllBasketsOfUser(principal.getName());
    }
}
