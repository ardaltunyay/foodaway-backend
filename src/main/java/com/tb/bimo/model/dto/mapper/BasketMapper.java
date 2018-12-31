package com.tb.bimo.model.dto.mapper;

import com.tb.bimo.model.dto.request.CreateBasketRequest;
import com.tb.bimo.model.persistance.Basket;
import org.mapstruct.Mapper;

@Mapper
public interface BasketMapper {

    Basket createBasketRequestToBasket(CreateBasketRequest createBasketRequest);
}
