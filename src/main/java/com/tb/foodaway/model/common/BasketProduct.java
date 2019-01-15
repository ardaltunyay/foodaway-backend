package com.tb.foodaway.model.common;

import com.tb.foodaway.model.persistance.Product;
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
