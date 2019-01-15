package com.tb.foodaway.model.dto.mapper;

import com.tb.foodaway.model.dto.request.CreateBasketRequest;
import com.tb.foodaway.model.persistance.Basket;
import org.mapstruct.Mapper;

@Mapper
public interface BasketMapper {

    Basket createBasketRequestToBasket(CreateBasketRequest createBasketRequest);
}
