package com.std.cov.coverages.model.dto;

import com.std.cov.coverages.model.entity.Coverage;

public class CoverageResponseDto {
    public static CoverageDto coverageResponse(Coverage coverage) {
        return CoverageDto.builder()
                .id(coverage.getId())
                .carrierId(coverage.getCarrierId())
                .carrierServiceId(coverage.getCarrierServiceId())
                .placeIdFrom(coverage.getPlaceIdFrom())
                .placeIdTo(coverage.getPlaceIdTo())
                .isBlocked(coverage.getIsBlocked())
                .createdAt(coverage.getCreatedAt())
                .updatedAt(coverage.getUpdatedAt())
                .deletedAt(coverage.getDeletedAt())
                .build();
    }
}
