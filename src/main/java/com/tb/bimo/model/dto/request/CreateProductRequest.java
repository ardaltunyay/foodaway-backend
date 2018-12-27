package com.tb.bimo.model.dto.request;

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
}
