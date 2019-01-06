package com.tb.bimo.model.dto.request;

import lombok.*;
import java.util.List;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateBranchRequest {

    private String branchName;

    private List<Double> location;
}
