package com.tb.foodaway.model.persistance;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tb.foodaway.model.common.BasketProduct;
import com.tb.foodaway.model.enums.OrderStatus;
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

    @JsonIgnore
    @NotNull
    private String branchId;

    @NotNull
    private String campaignId;

    private List<BasketProduct> productList;

    @NotNull
    private Double paidPrice;

    @NotNull
    private String paymentStatus;

    @NotNull
    private OrderStatus status;

    private Double preparingTime;

    @JsonIgnore
    @NotNull
    private String paymentId;

    @CreatedDate
    private DateTime dateCreated;

    @LastModifiedDate
    private DateTime dateModified;
}
