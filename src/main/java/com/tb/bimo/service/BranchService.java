package com.tb.bimo.service;

import com.tb.bimo.model.dto.request.CreateBranchRequest;
import com.tb.bimo.model.persistance.Branch;
import com.tb.bimo.repository.BranchRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metrics;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class BranchService {

    private final BranchRepository branchRepository;
    public void create(CreateBranchRequest createBranchRequest, String companyId) {
        Branch branch = new Branch();
        branch.setBranchName( createBranchRequest.getBranchName() );
        branch.setCompanyId(companyId);
        branch.setLocation( new GeoJsonPoint(createBranchRequest.getLocation().get(0), createBranchRequest.getLocation().get(1)) );

        branchRepository.save(branch);
    }

    public List<Branch> getBranchListByLocationNear(String latitude, String longitude, Double distance){
        return branchRepository.findByLocationNear(
                new GeoJsonPoint(
                        Double.valueOf(longitude),
                        Double.valueOf(latitude)
                ),
                new Distance(distance, Metrics.KILOMETERS)
        );
    }
}
