package com.tb.foodaway.model.dto.request;

import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeleteProductFromBasketRequest {

    @NotNull
    String branchId;

    @NotNull
    String productId;
}
