package com.tb.foodaway.model.dto.common;

import com.tb.foodaway.model.persistance.Product;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SubMenuWithProductObj {
    private String title;
    private List<Product> productList;
}
