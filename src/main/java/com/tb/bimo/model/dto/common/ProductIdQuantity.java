package com.tb.bimo.model.dto.common;

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
