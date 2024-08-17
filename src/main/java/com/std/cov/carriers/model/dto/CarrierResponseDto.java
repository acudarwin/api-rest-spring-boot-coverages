package com.std.cov.carriers.model.dto;

import com.std.cov.carriers.model.entity.Carrier;

public class CarrierResponseDto {
    public static CarrierDto carrierResponse(Carrier carrier) {
        return CarrierDto.builder()
                .id(carrier.getId())
                .cancelDelivery(carrier.getCancelDelivery())
                .code(carrier.getCode())
                .name(carrier.getName())
                .carrierManager(carrier.getCarrierManager())
                .createdAt(carrier.getCreatedAt())
                .updatedAt(carrier.getUpdatedAt())
                .deletedAt(carrier.getDeletedAt())
                .build();
    }
}
