package com.tb.bimo.model.dto.common;

import com.tb.bimo.model.persistance.Product;
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
