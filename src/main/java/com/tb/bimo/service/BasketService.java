package com.tb.bimo.service;

import com.tb.bimo.exception.BasketCompanyIdNotFoundException;
import com.tb.bimo.exception.BasketProductIdNotFoundException;
import com.tb.bimo.exception.ResourceNotFoundException;
import com.tb.bimo.model.common.BasketProduct;
import com.tb.bimo.model.dto.common.ProductIdQuantity;
import com.tb.bimo.model.dto.mapper.BasketMapper;
import com.tb.bimo.model.dto.request.CreateBasketRequest;
import com.tb.bimo.model.dto.request.UpdateBasketRequest;
import com.tb.bimo.model.persistance.Basket;
import com.tb.bimo.repository.BasketRepository;
import com.tb.bimo.repository.CompanyRepository;
import com.tb.bimo.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class BasketService {

    private final BasketRepository basketRepository;
    private final ProductRepository productRepository;
    private final CompanyRepository companyRepository;
    private BasketMapper basketMapper = Mappers.getMapper(BasketMapper.class);

    public Basket getBasketOfUserByCampaignId(String userId, String companyId) {
        log.info("Fetching basket for company {} of user: {}", companyId, userId);

        if (basketRepository.findByUserIdAndCompanyId(userId, companyId).isPresent()) {
            return basketRepository.findByUserIdAndCompanyId(userId, companyId).get();
        } else {
            log.info("Basket not found for userId {} and companyId {}", userId, companyId);
            throw  new ResourceNotFoundException("Basket not found for this company.");
        }
    }

    public List<Basket> getAllBasketsOfUser(String userId) {
        log.info("Fetching all available baskets of user: {}", userId);

        return basketRepository.findAllByUserId(userId);
    }

    public Basket createBasket(String userId, CreateBasketRequest createBasketRequest) {
        Basket basket = basketMapper.createBasketRequestToBasket(createBasketRequest);

        List<BasketProduct> productList = new ArrayList<>();
        createBasketRequest.getProducts().forEach(productIdQuantity -> {
            productList.add(mapProductIdQuantityToBasketProduct(productIdQuantity));
        });

        if (productList.iterator().hasNext()) {
            basket.setProductList(productList);
        }

        basket.setCompanyName(companyRepository.findById(createBasketRequest.getCompanyId()).orElseThrow(() -> {
            log.error("Invalid company id {} given to create basket.", createBasketRequest.getCompanyId());
            return new BasketCompanyIdNotFoundException("Invalid company id given.");
        }).getTitle());

        basket.setUserId(userId);
        return basketRepository.save(basket);
    }

    public Basket updateBasket(String userId, UpdateBasketRequest updateBasketRequest) {
        Basket basket = basketRepository.findById(updateBasketRequest.getId()).orElseThrow(() -> new ResourceNotFoundException("Basket not found for given id."));

        List<BasketProduct> productList = new ArrayList<>();
        updateBasketRequest.getProducts().forEach(productIdQuantity -> {
            productList.add(mapProductIdQuantityToBasketProduct(productIdQuantity));
        });

        if (productList.iterator().hasNext()) {
            basket.setProductList(productList);
        }

        basket.setCampaignId(updateBasketRequest.getCampaignId());
        basket.setUserId(userId);
        return basketRepository.save(basket);
    }

    // TODO Move custom mapping methods to the BasketMapper
    private BasketProduct mapProductIdQuantityToBasketProduct(ProductIdQuantity productIdQuantity) {
        return BasketProduct
                .builder()
                .product(productRepository.findById(productIdQuantity.getProductId()).orElseThrow(() -> {
                    log.error("Invalid product id {} given to add basket.", productIdQuantity.getProductId());
                    return new BasketProductIdNotFoundException("Invalid product id given.");
                }))
                .quantity(productIdQuantity.getQuantity())
                .build();
    }
}
