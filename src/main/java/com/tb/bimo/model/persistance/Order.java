package com.tb.bimo.model.persistance;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tb.bimo.model.common.BasketProduct;
import lombok.*;
import org.joda.time.DateTime;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document
public class Order {

    @Id
    private String id;

    @NotNull
    @Indexed(unique = true)
    private String orderNumber;

    @JsonIgnore
    @NotNull
    private String userId;

    @JsonIgnore
    @NotNull
    private String companyId;

    @NotNull
    private String companyName;

    @NotNull
    private String campaignId;

    private List<BasketProduct> productList;

    @NotNull
    private Double totalPrice;

    @CreatedDate
    private DateTime dateCreated;

    @LastModifiedDate
    private DateTime dateModified;
}
