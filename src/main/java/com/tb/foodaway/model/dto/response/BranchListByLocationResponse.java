package com.tb.foodaway.model.dto.response;

import com.tb.foodaway.model.persistance.Branch;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BranchListByLocationResponse {

    List<Branch> branchList;
}
