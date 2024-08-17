package com.std.cov.places.model.dto;

import com.std.cov.places.model.entity.Place;

import java.io.Serializable;

public class PlaceResponseDto implements Serializable {

    public static PlaceDto placeResponse(Place place) {
        return PlaceDto.builder()
                .id(place.getId())
                .nameFrom(place.getNameFrom())
                .nameTo(place.getNameTo())
                .customization(place.getCustomization())
                .createdAt(place.getCreatedAt())
                .updatedAt(place.getUpdatedAt())
                .deletedAt(place.getDeletedAt())
                .build();
    }

}
