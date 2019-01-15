package com.tb.foodaway.model.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WebAuthResponse {
    String authorizedBranchId;
}
