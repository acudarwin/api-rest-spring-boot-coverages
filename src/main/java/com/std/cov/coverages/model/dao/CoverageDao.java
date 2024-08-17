package com.std.cov.coverages.model.dao;

import com.std.cov.coverages.model.entity.Coverage;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CoverageDao extends CrudRepository<Coverage, Integer> {
    List<Coverage> findByCarrierIdAndCarrierServiceIdAndPlaceIdFromAndPlaceIdTo(Integer carrierId, Integer carrierServiceId, Integer placeIdFrom, Integer placeIdTo);
}
