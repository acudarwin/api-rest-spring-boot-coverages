package com.std.cov.carrier_services.service;

import com.std.cov.carrier_services.model.dto.CarrierServiceDto;
import com.std.cov.carrier_services.model.dto.CarrierServiceUpdateDto;
import com.std.cov.carrier_services.model.entity.CarrierService;

import java.util.List;

public interface ICarrierService {

    List<CarrierService> listAll();

    CarrierService save(CarrierServiceDto carrierService);

    CarrierService update(CarrierServiceUpdateDto carrierService);

    CarrierService findById(Integer id);

    void delete(CarrierService carrierService);

    boolean existsCarrierServiceId(Integer id);

}
