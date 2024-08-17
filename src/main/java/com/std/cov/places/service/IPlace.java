package com.std.cov.places.service;

import com.std.cov.carriers.model.entity.Carrier;
import com.std.cov.places.model.dto.PlaceDto;
import com.std.cov.places.model.dto.PlaceUpdateDto;
import com.std.cov.places.model.entity.Place;

import java.util.List;

public interface IPlace {

    List<Place> listAll();

    Place save(PlaceDto place);

    Place update(PlaceUpdateDto place);

    Place findById(Integer id);

    void delete(Place place);

    boolean existsPlaceId(Integer id);

}
