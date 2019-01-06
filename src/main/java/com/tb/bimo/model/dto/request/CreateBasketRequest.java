package com.tb.bimo.model.dto.request;

import com.tb.bimo.model.dto.common.ProductIdQuantity;
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


