package com.tb.bimo.controller.backoffice;

import com.tb.bimo.model.dto.request.CreateProductRequest;
import com.tb.bimo.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/backoffice/company/{companyId}/product")
public class ProductBackofficeController {

    private final ProductService productService;

    @PostMapping
    @PreAuthorize("hasRole('ROLE_CONTENT_MANAGER') and isCompanyAuthorized(#companyId)")
    public void addProduct(@RequestBody CreateProductRequest createProductRequest, @PathVariable String companyId) {
        log.info("Adding product for company: {}", companyId);
        productService.create(createProductRequest, companyId);
    }
}
