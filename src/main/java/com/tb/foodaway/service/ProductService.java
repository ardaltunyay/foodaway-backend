package com.tb.foodaway.service;

import com.tb.foodaway.model.dto.mapper.ProductMapper;
import com.tb.foodaway.model.dto.request.CreateProductRequest;
import com.tb.foodaway.model.persistance.Product;
import com.tb.foodaway.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;

@Slf4j
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private ProductMapper productMapper = Mappers.getMapper(ProductMapper.class);

    public void create(CreateProductRequest createProductRequest, String companyId) {
        Product product = productMapper.createProductRequestToProduct(createProductRequest);
        product.setCompanyId(companyId);

        productRepository.save(product);
    }
}
