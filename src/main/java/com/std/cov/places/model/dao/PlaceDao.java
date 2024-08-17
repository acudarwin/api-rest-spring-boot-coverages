package com.std.cov.places.model.dao;

import com.std.cov.places.model.entity.Place;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PlaceDao extends CrudRepository<Place, Integer> {
    List<Place> findByCustomization(String customization);
}
