package com.tb.bimo.model.dto.request;

import lombok.*;

import javax.validation.constraints.NotNull;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateCampaignRequest {

    @NotNull
    private String title;
    @NotNull
    private String description;
    @NotNull
    private String imageUrl;
    private Double discountRate;
}
