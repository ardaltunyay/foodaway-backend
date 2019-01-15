package com.tb.foodaway.model.dto.common;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductIdQuantity {
    String productId;
    Long quantity;
}
