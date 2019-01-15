package com.tb.foodaway.model.dto.response;

import com.tb.foodaway.model.dto.common.SubMenuWithProductObj;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CompanyMenuResponse {

    private String companyName;
    private String companyLogoUrl;
    private List<SubMenuWithProductObj> menuList;
}
