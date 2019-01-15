package com.tb.foodaway.model.dto.request;

import com.tb.foodaway.model.dto.common.ProductIdQuantity;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateBasketRequest {

    @NotNull
    String companyId;

    @NotNull
    String branchId;

    String campaignId;

    List<ProductIdQuantity> products;
}


