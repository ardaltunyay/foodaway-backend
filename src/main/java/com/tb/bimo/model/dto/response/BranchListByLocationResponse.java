package com.tb.bimo.model.dto.response;

import com.tb.bimo.model.persistance.Branch;
import lombok.*;

import javax.validation.constraints.Max;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BranchListByLocationResponse {

    List<Branch> branchList;
}
