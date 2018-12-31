package com.tb.bimo.model.common;

import com.tb.bimo.model.persistance.Product;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class BasketProduct {

    Product product;
    Long quantity;
}
