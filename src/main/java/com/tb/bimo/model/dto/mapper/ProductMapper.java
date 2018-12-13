package com.tb.bimo.model.dto.mapper;

import com.tb.bimo.model.persistance.Product;
import com.tb.bimo.model.dto.CreateProductRequest;
import org.mapstruct.Mapper;

@Mapper
public interface ProductMapper {

    Product createProductRequestToProduct(CreateProductRequest createProductRequest);

    CreateProductRequest productToCreateProductRequest(Product product);
}

