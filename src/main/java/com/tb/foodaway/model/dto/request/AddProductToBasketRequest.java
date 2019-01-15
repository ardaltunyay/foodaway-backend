package com.tb.foodaway.model.dto.request;

import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddProductToBasketRequest {

    @NotNull
    String branchId;

    String campaignId;

    @NotNull
    String productId;

    @NotNull
    Long quantity;
}
