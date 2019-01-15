package com.tb.foodaway.model.dto.request;

import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FindByLocationRequest {

    private String latitude;

    private String longitude;

    private double distance;
}
