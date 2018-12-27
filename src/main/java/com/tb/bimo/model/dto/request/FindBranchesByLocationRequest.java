package com.tb.bimo.model.dto.request;

import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FindBranchesByLocationRequest {

    private String latitude;

    private String longitude;

    private double distance;
}
