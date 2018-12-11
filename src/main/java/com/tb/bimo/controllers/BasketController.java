package com.tb.bimo.controllers;

import com.tb.bimo.models.Basket;
import com.tb.bimo.models.Item;
import com.tb.bimo.repositories.BasketRepository;
import com.tb.bimo.repositories.ItemRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/basket")
@Api(value="basket", description="CRUD for customer basket")
public class BasketController {

    @Autowired
    BasketRepository basketRepository;

    @Autowired
    ItemRepository itemRepository;

    @ApiOperation(value = "Get customer basket", response = Basket.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved basket"),
            @ApiResponse(code = 404, message = "There is no user in database")
    })
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public Basket getBasket() {

        Basket basket = new Basket();
        Item item1 = Item.builder()
                .title("item1")
                .price(9.00)
                .description("item1 desc")
                .build();
        Item item2 = Item.builder()
                .title("item2")
                .price(21.00)
                .description("item2 desc")
                .build();

        itemRepository.save(item1);
        itemRepository.save(item2);

        basket.setCreatedAt(new Date().getTime());

        Long basketId = basketRepository.save(basket).getId();

        Basket savedBasket = basketRepository.findById(basketId).get();

        savedBasket.addItem(item1, 3);
        savedBasket.addItem(item2, 2);

        basketRepository.save(savedBasket);

        return savedBasket;
    }
}
