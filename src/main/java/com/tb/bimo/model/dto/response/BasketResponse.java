package com.tb.bimo.model.dto.response;

import com.tb.bimo.model.common.BasketProduct;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BasketResponse {

    private String id;
    private String companyId;
    private String companyName;
    private String branchId;
    private String campaignId;
    private List<BasketProduct> productList;
    private String dateAdded;
    private String dateModified;
    private String paidPrice;
    private String totalPrice;
}
