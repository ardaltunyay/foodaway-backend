package com.tb.foodaway.model.dto.request;

import lombok.*;

import javax.validation.constraints.NotNull;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlaceOrderRequest {

    @NotNull
    private String basketId;
    @NotNull
    private String cardHolderName;
    @NotNull
    private String cardNumber;
    @NotNull
    private String expireMonth;
    @NotNull
    private String expireYear;
    @NotNull
    private String cvc;
    @NotNull
    private boolean registerCard;
}
