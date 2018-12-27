package com.tb.bimo.repository;

import com.tb.bimo.model.persistance.Branch;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface BranchRepository extends MongoRepository<Branch, String> {

    List<Branch> findByLocationNear(Point point, Distance distance);
}
