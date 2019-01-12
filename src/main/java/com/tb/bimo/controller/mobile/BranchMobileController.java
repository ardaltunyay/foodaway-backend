package com.tb.bimo.controller.mobile;

import com.tb.bimo.model.dto.request.FindByLocationRequest;
import com.tb.bimo.model.dto.response.BranchListByLocationResponse;
import com.tb.bimo.model.persistance.Branch;
import com.tb.bimo.service.BranchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/mobile/branch")
public class BranchMobileController {

    private final BranchService branchService;

    @GetMapping
    public BranchListByLocationResponse getBranchListByLocationNear(@RequestParam FindByLocationRequest findByLocationRequest) {
        return BranchListByLocationResponse.builder().branchList(branchService.getBranchListByLocationNear(
                findByLocationRequest.getLatitude(),
                findByLocationRequest.getLongitude(),
                findByLocationRequest.getDistance()
        )).build();
    }
}
