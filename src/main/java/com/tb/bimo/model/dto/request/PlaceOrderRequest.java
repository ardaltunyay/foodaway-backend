package com.tb.bimo.model.dto.request;

import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlaceOrderRequest {

    private String basketId;
    private String cardHolderName;
    private String cardNumber;
    private String expireMonth;
    private String expireYear;
    private String cvc;
    private boolean registerCard;
}
