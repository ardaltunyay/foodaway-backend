package com.tb.bimo.controller.mobile;

import com.tb.bimo.model.dto.response.BranchListByLocationResponse;
import com.tb.bimo.service.BranchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/mobile/branch")
public class BranchMobileController {

    private final BranchService branchService;

    @GetMapping
    public BranchListByLocationResponse getBranchListByLocationNear(@RequestParam String latitude, @RequestParam String longitude, @RequestParam String distance) {
        return BranchListByLocationResponse.builder().branchList(branchService.getBranchListByLocationNear(
                latitude,
                longitude,
                Double.valueOf(distance)
        )).build();
    }
}
