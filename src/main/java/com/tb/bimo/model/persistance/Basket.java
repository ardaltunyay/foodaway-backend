package com.tb.bimo.model.persistance;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tb.bimo.model.common.BasketProduct;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document
public class Basket {

    @Id
    private String id;

    @JsonIgnore
    @NotNull
    private String userId;

    @NotNull
    private String companyId;

    @NotNull
    private String companyName;

    @NotNull
    private String campaignId;

    private List<BasketProduct> productList;
}
