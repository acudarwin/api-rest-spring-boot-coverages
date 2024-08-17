package com.std.cov.carriers.service;

import com.std.cov.carriers.model.dto.CarrierDto;
import com.std.cov.carriers.model.dto.CarrierUpdateDto;
import com.std.cov.carriers.model.entity.Carrier;

import java.util.List;

public interface ICarrier {

    List<Carrier> listAll();

    Carrier save(CarrierDto carrier);

    Carrier update(CarrierUpdateDto carrier);

    Carrier findById(Integer id);

    void delete(Carrier carrier);

    boolean existsCarrierId(Integer id);
}
