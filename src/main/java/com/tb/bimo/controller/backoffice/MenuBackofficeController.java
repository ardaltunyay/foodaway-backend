package com.tb.bimo.controller.backoffice;

import com.tb.bimo.model.dto.request.CreateMenuRequest;
import com.tb.bimo.service.MenuService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/backoffice/company/{companyId}/menu")
public class MenuBackofficeController {

    private final MenuService menuService;

    @PostMapping
    @PreAuthorize("hasRole('ROLE_CONTENT_MANAGER') and isCompanyAuthorized(#companyId)")
    public void addMenu(@RequestBody CreateMenuRequest createMenuRequest, @PathVariable String companyId) {
        menuService.createMenu(createMenuRequest, companyId);
    }
}
