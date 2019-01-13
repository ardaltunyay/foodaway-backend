package com.tb.bimo.service;

import com.tb.bimo.exception.BasketCompanyIdNotFoundException;
import com.tb.bimo.exception.BasketProductIdNotFoundException;
import com.tb.bimo.exception.ResourceAlreadyExistsException;
import com.tb.bimo.exception.ResourceNotFoundException;
import com.tb.bimo.model.common.BasketProduct;
import com.tb.bimo.model.dto.common.ProductIdQuantity;
import com.tb.bimo.model.dto.mapper.BasketMapper;
import com.tb.bimo.model.dto.request.AddProductToBasketRequest;
import com.tb.bimo.model.dto.request.CreateBasketRequest;
import com.tb.bimo.model.dto.request.DeleteProductFromBasketRequest;
import com.tb.bimo.model.dto.request.UpdateBasketRequest;
import com.tb.bimo.model.persistance.Basket;
import com.tb.bimo.model.persistance.Branch;
import com.tb.bimo.model.persistance.Company;
import com.tb.bimo.repository.BasketRepository;
import com.tb.bimo.repository.BranchRepository;
import com.tb.bimo.repository.CompanyRepository;
import com.tb.bimo.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;

import java.util.*;

@Slf4j
@RequiredArgsConstructor
public class BasketService {

    private final BasketRepository basketRepository;
    private final ProductRepository productRepository;
    private final CompanyRepository companyRepository;
    private final BranchRepository branchRepository;
    private BasketMapper basketMapper = Mappers.getMapper(BasketMapper.class);

    public Basket getBasketOfUserByBranchId(String userId, String branchId) {
        log.info("Fetching basket for company {} of user: {}", branchId, userId);

        if (basketRepository.findByUserIdAndBranchId(userId, branchId).isPresent()) {
            return basketRepository.findByUserIdAndBranchId(userId, branchId).get();
        } else {
            log.info("Basket not found for userId {} and companyId {}", userId, branchId);
            throw  new ResourceNotFoundException("Basket not found for this company.");
        }
    }

    public List<Basket> getAllBasketsOfUser(String userId) {
        log.info("Fetching all available baskets of user: {}", userId);

        return basketRepository.findAllByUserId(userId);
    }

    public Basket createBasket(String userId, CreateBasketRequest createBasketRequest) {
        Basket basket = basketMapper.createBasketRequestToBasket(createBasketRequest);
        if (basketRepository.findByUserIdAndBranchId(userId, createBasketRequest.getBranchId()).isPresent()) {
            throw new ResourceAlreadyExistsException("Basket already exists with given branch.");
        }

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

    public boolean deleteAllBasketsOfUser(String userId) {
        return basketRepository.deleteAllByUserId(userId);
    }

    public boolean deleteBasketOfUserByBranchId(String userId, String companyId){
        return basketRepository.deleteByUserIdAndAndBranchId(userId, companyId);
    }

    private BasketProduct mapProductIdQuantityToBasketProduct(ProductIdQuantity productIdQuantity) {
        return BasketProduct.builder()
                .product(productRepository.findById(productIdQuantity.getProductId()).orElseThrow(() -> {
                    log.error("Invalid product id {} given to add basket.", productIdQuantity.getProductId());
                    return new BasketProductIdNotFoundException("Invalid product id given.");
                }))
                .quantity(productIdQuantity.getQuantity())
                .build();
    }

    public void addProductToBasket(String userId, AddProductToBasketRequest addProductToBasketRequest) {
        Branch branch = branchRepository.findById(addProductToBasketRequest.getBranchId()).orElseThrow(
                () -> new ResourceNotFoundException("Branch not found."));
        Company company = companyRepository.findById(branch.getCompanyId()).orElseThrow(
                () -> new ResourceNotFoundException("Company not found."));

        Optional<Basket> basket = basketRepository.findByUserIdAndBranchId(userId, addProductToBasketRequest.getBranchId());

        if (basket.isPresent()) {
            boolean productExists = false;
            for (BasketProduct basketProduct : basket.get().getProductList()) {
                if (basketProduct.getProduct().getId().equals(addProductToBasketRequest.getProductId())) {
                    productExists = true;
                    basketProduct.setQuantity(basketProduct.getQuantity() + addProductToBasketRequest.getQuantity());
                }
            }
            if (!productExists) basket.get().getProductList().add(BasketProduct.builder()
                    .product(productRepository.findById(addProductToBasketRequest.getProductId()).orElseThrow(() -> {
                        log.error("Invalid product id {} given to add basket.", addProductToBasketRequest.getProductId());
                        return new BasketProductIdNotFoundException("Invalid product id given.");
                    }))
                    .quantity(addProductToBasketRequest.getQuantity())
                    .build());
            basketRepository.save(basket.get());
        } else {
            basketRepository.save(
                    Basket.builder()
                            .branchId(addProductToBasketRequest.getBranchId())
                            .campaignId(addProductToBasketRequest.getCampaignId())
                            .companyId(company.getId())
                            .companyName(company.getTitle())
                            .productList(
                                    Collections.singletonList(BasketProduct.builder()
                                            .product(productRepository.findById(addProductToBasketRequest.getProductId()).orElseThrow(() -> {
                                                log.error("Invalid product id {} given to add basket.", addProductToBasketRequest.getProductId());
                                                return new BasketProductIdNotFoundException("Invalid product id given.");
                                            }))
                                            .quantity(addProductToBasketRequest.getQuantity())
                                            .build())
                            )
                            .userId(userId)
                            .build()
            );
        }
    }

    public void deleteProductFromBasket(String userId, DeleteProductFromBasketRequest deleteProductFromBasketRequest) {
        Basket basket = basketRepository.findByUserIdAndBranchId(userId, deleteProductFromBasketRequest.getBranchId()).orElseThrow(() -> new ResourceNotFoundException("Basket not found."));
        for (BasketProduct basketProduct : basket.getProductList()) {
            if (basketProduct.getProduct().getId().equals(deleteProductFromBasketRequest.getProductId()))
                basket.getProductList().remove(basketProduct);
        }
        basketRepository.save(basket);
    }

    public void setProductOnBasket(String userId, AddProductToBasketRequest addProductToBasketRequest) {
        Basket basket = basketRepository.findByUserIdAndBranchId(userId, addProductToBasketRequest.getBranchId()).orElseThrow(() -> new ResourceNotFoundException("Basket not found."));
        for (BasketProduct basketProduct : basket.getProductList()) {
            if (basketProduct.getProduct().getId().equals(addProductToBasketRequest.getProductId()))
                basketProduct.setQuantity(addProductToBasketRequest.getQuantity());
        }
        basketRepository.save(basket);
    }
}
