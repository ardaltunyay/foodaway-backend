package com.tb.bimo.model.dto.mapper;

import com.tb.bimo.model.persistance.Product;
import com.tb.bimo.model.dto.request.CreateProductRequest;
import org.mapstruct.Mapper;

@Mapper
public interface ProductMapper {

    Product createProductRequestToProduct(CreateProductRequest createProductRequest);
}

