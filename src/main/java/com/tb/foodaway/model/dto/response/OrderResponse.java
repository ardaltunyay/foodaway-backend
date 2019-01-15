package com.tb.foodaway.model.dto.response;

import com.tb.foodaway.model.common.BasketProduct;
import com.tb.foodaway.model.enums.OrderStatus;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderResponse {

    private String orderNumber;
    private String companyName;
    private List<BasketProduct> productList;
    private Double paidPrice;
    private OrderStatus status;
    private String dateCreated;
    private String dateReady;
}
