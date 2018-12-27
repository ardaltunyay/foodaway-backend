package com.tb.bimo.model.dto.mapper;

import com.tb.bimo.model.dto.request.CreateBranchRequest;
import com.tb.bimo.model.persistance.Branch;
import org.mapstruct.Mapper;

@Mapper
public interface BranchMapper {

    Branch createBranchRequestToBranch(CreateBranchRequest createBranchRequest);
}
