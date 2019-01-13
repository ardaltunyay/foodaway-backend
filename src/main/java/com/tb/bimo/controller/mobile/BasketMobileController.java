package com.tb.bimo.controller.mobile;

import com.tb.bimo.model.dto.request.AddProductToBasketRequest;
import com.tb.bimo.model.dto.request.CreateBasketRequest;
import com.tb.bimo.model.dto.request.DeleteProductFromBasketRequest;
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
public class BasketMobileController {

    private final BasketService basketService;

    @GetMapping
    public Basket getBasketOfUserByBranchId(@RequestParam String branchId, Principal principal) {
        return basketService.getBasketOfUserByBranchId(principal.getName(), branchId);
    }

    @GetMapping("/all")
    public List<Basket> getAllBasketsOfUser(Principal principal) {
        return basketService.getAllBasketsOfUser(principal.getName());
    }

    @PostMapping("/add-product")
    public void addProductToBasket(@RequestBody AddProductToBasketRequest addProductToBasketRequest, Principal principal) {
        basketService.addProductToBasket(principal.getName(), addProductToBasketRequest);
    }

    @DeleteMapping("/delete-product")
    public void addProductToBasket(@RequestBody DeleteProductFromBasketRequest deleteProductFromBasketRequest, Principal principal) {
        basketService.deleteProductFromBasket(principal.getName(), deleteProductFromBasketRequest);
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
    public void deleteBasketOfUserByBranchId(@RequestParam String branchId, Principal principal) {
        basketService.deleteBasketOfUserByBranchId(principal.getName(), branchId);
    }

    @DeleteMapping("/all")
    public void deleteAllBasketsOfUser(Principal principal) {
        basketService.deleteAllBasketsOfUser(principal.getName());
    }
}
