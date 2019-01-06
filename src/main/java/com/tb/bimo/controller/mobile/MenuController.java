package com.tb.bimo.controller.mobile;

import com.tb.bimo.model.dto.response.CompanyMenuResponse;
import com.tb.bimo.service.MenuService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/mobile/menu")
public class MenuController {

    private final MenuService menuService;

    @GetMapping
    public CompanyMenuResponse getMenuByBranchId(@RequestParam String branchId) {
        return menuService.getMenuByBranchId(branchId);
    }
}
