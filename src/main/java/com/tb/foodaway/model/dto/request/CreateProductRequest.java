package com.tb.foodaway.model.dto.request;

import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateProductRequest {

    private String title;

    private String description;

    private String price;

    private String logoUrl;
}
