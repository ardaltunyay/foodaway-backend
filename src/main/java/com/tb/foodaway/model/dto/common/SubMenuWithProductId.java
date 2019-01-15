package com.tb.foodaway.model.dto.common;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SubMenuWithProductId {

    private String title;
    private List<String> productList;
}
