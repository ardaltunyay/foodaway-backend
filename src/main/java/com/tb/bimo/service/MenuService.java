package com.tb.bimo.service;

import com.tb.bimo.exception.ResourceNotFoundException;
import com.tb.bimo.model.dto.common.SubMenuWithProductId;
import com.tb.bimo.model.dto.common.SubMenuWithProductObj;
import com.tb.bimo.model.dto.mapper.MenuMapper;
import com.tb.bimo.model.dto.request.CreateMenuRequest;
import com.tb.bimo.model.dto.response.CompanyMenuResponse;
import com.tb.bimo.model.persistance.Branch;
import com.tb.bimo.model.persistance.Company;
import com.tb.bimo.model.persistance.Menu;
import com.tb.bimo.model.persistance.Product;
import com.tb.bimo.repository.BranchRepository;
import com.tb.bimo.repository.CompanyRepository;
import com.tb.bimo.repository.MenuRepository;
import com.tb.bimo.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class MenuService {

    private final MenuRepository menuRepository;
    private final ProductRepository productRepository;
    private final CompanyRepository companyRepository;
    private final BranchRepository branchRepository;
    private MenuMapper menuMapper = Mappers.getMapper(MenuMapper.class);

    public CompanyMenuResponse getMenuByBranchId(String branchId) {

        Menu menu = menuRepository.findByBranchId(branchId)
                .orElse(menuRepository.findByCompanyId(
                        branchRepository.findById(branchId).orElseThrow(() -> new ResourceNotFoundException("Branch not found.")).getCompanyId()
                ).orElseThrow(() -> new ResourceNotFoundException("Menu not found.")));

        List<SubMenuWithProductObj> subMenuWithProductObjList = new ArrayList<>();
        menu.getSubMenuList().forEach(submenu -> subMenuWithProductObjList.add(mapSubMenuWithProductIdToSubMenuWithProductObj(submenu)));

        return CompanyMenuResponse.builder()
                .companyName(menu.getCompanyName())
                .companyLogoUrl(menu.getCompanyLogoUrl())
                .menuList(subMenuWithProductObjList)
                .build();
    }

    private SubMenuWithProductObj mapSubMenuWithProductIdToSubMenuWithProductObj(SubMenuWithProductId subMenuWithProductId) {

        List<Product> productList = new ArrayList<>();
        productRepository.findAllById(subMenuWithProductId.getProductList()).forEach(productList::add);

        return SubMenuWithProductObj.builder()
                .title(subMenuWithProductId.getTitle())
                .productList(productList)
                .build();
    }

    public String createMenu(CreateMenuRequest createMenuRequest, String companyId) {
        Company company = companyRepository.findById(companyId).orElseThrow(() -> new ResourceNotFoundException("Company not found."));

        if (createMenuRequest.getBranchId() != null) {
            Branch branch = branchRepository.findByIdAndCompanyId(createMenuRequest.getBranchId(), companyId)
                    .orElseThrow(() -> new ResourceNotFoundException("Branch not found"));

            Menu menu = menuMapper.createMenuRequestToMenu(createMenuRequest);
            menu.setCompanyId(companyId);
            menu.setCompanyName(company.getTitle());
            menu.setCompanyLogoUrl(company.getLogoUrl());

            String menuId = menuRepository.save(menu).getId();

            branch.setMenuId(menuId);
            branchRepository.save(branch);

            return menuId;
        } else {
            Menu menu = menuMapper.createMenuRequestToMenu(createMenuRequest);
            menu.setCompanyId(companyId);
            menu.setCompanyName(company.getTitle());
            menu.setCompanyLogoUrl(company.getLogoUrl());

            return menuRepository.save(menu).getId();
        }
    }
}
