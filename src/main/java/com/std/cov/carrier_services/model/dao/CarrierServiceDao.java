package com.std.cov.carrier_services.model.dao;

import com.std.cov.carrier_services.model.entity.CarrierService;
import com.std.cov.coverages.model.entity.Coverage;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CarrierServiceDao extends CrudRepository<CarrierService, Integer> {
    @EntityGraph(attributePaths = {"carrier"})
    List<CarrierService> findAll();
    CarrierService findByIdAndCarrierId(Integer carrierId, Integer carrierServiceId);
    List<CarrierService> findByCodeAndCarrierId(String code, Integer carrierId);
}
