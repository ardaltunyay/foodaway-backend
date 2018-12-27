package com.tb.bimo.model.dto.request;

import lombok.*;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateBranchRequest {

    private String branchName;

    private String companyId;

    private GeoJsonPoint location;
}
