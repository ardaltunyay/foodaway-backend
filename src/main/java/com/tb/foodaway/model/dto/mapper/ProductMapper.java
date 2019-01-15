package com.tb.foodaway.model.dto.mapper;

import com.tb.foodaway.model.dto.request.CreateProductRequest;
import com.tb.foodaway.model.persistance.Product;
import org.mapstruct.Mapper;

@Mapper
public interface ProductMapper {

    Product createProductRequestToProduct(CreateProductRequest createProductRequest);
}

