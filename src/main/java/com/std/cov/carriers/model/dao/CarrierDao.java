package com.std.cov.carriers.model.dao;

import com.std.cov.carriers.model.entity.Carrier;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CarrierDao extends CrudRepository<Carrier, Integer> {
    List<Carrier> findByCode(String code);
}
