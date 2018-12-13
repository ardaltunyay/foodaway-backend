package com.tb.bimo.service;

import com.tb.bimo.model.persistance.Product;
import com.tb.bimo.model.dto.CreateProductRequest;
import com.tb.bimo.model.dto.mapper.ProductMapper;
import com.tb.bimo.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;

@Slf4j
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private ProductMapper productMapper = Mappers.getMapper(ProductMapper.class);

    public void create(CreateProductRequest createProductRequest) {
        // TODO: Check for exceptions.
        // TODO: Add companyId from principal authorized company.
        Product product = productMapper.createProductRequestToProduct(createProductRequest);
        product.setCompanyId("T104");

        productRepository.save(product);
    }
}
