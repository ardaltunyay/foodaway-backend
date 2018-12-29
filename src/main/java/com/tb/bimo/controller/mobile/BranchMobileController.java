package com.tb.bimo.controller.mobile;

import com.tb.bimo.model.dto.request.FindByLocationRequest;
import com.tb.bimo.model.persistance.Branch;
import com.tb.bimo.service.BranchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/mobile/branch")
public class BranchMobileController {

    private final BranchService branchService;

    @GetMapping
    public List<Branch> getBranchListByLocationNear(@RequestBody FindByLocationRequest findByLocationRequest) {
        return branchService.getBranchListByLocationNear(
                findByLocationRequest.getLatitude(),
                findByLocationRequest.getLongitude(),
                findByLocationRequest.getDistance()
        );
    }
}
