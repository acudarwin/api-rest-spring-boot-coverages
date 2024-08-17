package com.std.cov.places.service.impl;

import com.std.cov.places.model.dao.PlaceDao;
import com.std.cov.places.model.dto.PlaceDto;
import com.std.cov.places.model.dto.PlaceUpdateDto;
import com.std.cov.places.model.entity.Place;
import com.std.cov.places.service.IPlace;
import com.std.cov.utils.PlaceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PlaceImplService implements IPlace {

    @Autowired
    private PlaceDao placeDao;

    @Override
    public List<Place> listAll() {
        return (List) placeDao.findAll();
    }

    @Transactional
    @Override
    public Place save(PlaceDto placeDto) {
        List<Place> validateExistsPlace = placeDao.findByCustomization(placeDto.getCustomization());
        if(validateExistsPlace != null && !validateExistsPlace.isEmpty()) {
            throw new PlaceException("Place with already exists.");
        } else {
            Place place = Place.builder()
                    .id(placeDto.getId())
                    .nameFrom(placeDto.getNameFrom())
                    .nameTo(placeDto.getNameTo())
                    .customization(placeDto.getCustomization())
                    .build();
            place.setCreatedAt(LocalDateTime.now());
            place.setUpdatedAt(LocalDateTime.now());
            return placeDao.save(place);
        }
    }

    @Transactional
    @Override
    public Place update(PlaceUpdateDto placeDto) {
        Place existingPlace = placeDao.findById(placeDto.getId()).orElseThrow(() -> new RuntimeException("Place Not Found"));
        Place place = Place.builder()
                .id(placeDto.getId())
                .nameFrom(placeDto.getNameFrom())
                .nameTo(placeDto.getNameTo())
                .customization(placeDto.getCustomization())
                .createdAt(existingPlace.getCreatedAt())
                .updatedAt(placeDto.getUpdatedAt())
                .deletedAt(placeDto.getDeletedAt())
                .build();
        place.setUpdatedAt(LocalDateTime.now());
        return placeDao.save(place);
    }

    @Transactional(readOnly = true)
    @Override
    public Place findById(Integer id) {
        return placeDao.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public void delete(Place place) {
        placeDao.delete(place);
    }

    @Override
    public boolean existsPlaceId(Integer id) {
        return placeDao.existsById(id);
    }
}
