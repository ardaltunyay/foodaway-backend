package com.tb.bimo.controller.mobile;

import com.tb.bimo.model.dto.request.AddProductToBasketRequest;
import com.tb.bimo.model.dto.request.CreateBasketRequest;
import com.tb.bimo.model.dto.request.DeleteProductFromBasketRequest;
import com.tb.bimo.model.dto.request.UpdateBasketRequest;
import com.tb.bimo.model.dto.response.BasketResponse;
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
    public List<BasketResponse> getAllBasketsOfUser(Principal principal) {
        return basketService.getAllBasketsOfUser(principal.getName());
    }

    @PostMapping("/add-product")
    public void addProductToBasket(@RequestBody AddProductToBasketRequest addProductToBasketRequest, Principal principal) {
        basketService.addProductToBasket(principal.getName(), addProductToBasketRequest);
    }

    @DeleteMapping("/delete-product")
    public void deleteProductFromBasket(@RequestBody DeleteProductFromBasketRequest deleteProductFromBasketRequest, Principal principal) {
        basketService.deleteProductFromBasket(principal.getName(), deleteProductFromBasketRequest);
    }

    @PutMapping("/set-product")
    public void setProductOnBasket(@RequestBody AddProductToBasketRequest addProductToBasketRequest, Principal principal) {
        basketService.setProductOnBasket(principal.getName(), addProductToBasketRequest);
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
