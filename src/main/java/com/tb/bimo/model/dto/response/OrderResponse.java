package com.tb.bimo.model.dto.response;

import com.tb.bimo.model.common.BasketProduct;
import com.tb.bimo.model.enums.OrderStatus;
import lombok.*;
import org.joda.time.DateTime;

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
