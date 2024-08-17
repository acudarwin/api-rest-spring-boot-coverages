package com.std.cov.carrier_services.model.dto;

import com.std.cov.carrier_services.model.entity.CarrierService;

public class CarrierServiceResponseDto {
    public static CarrierServiceDto carrierServiceResponse(CarrierService carrierService) {
        return CarrierServiceDto.builder()
                .id(carrierService.getId())
                .carrierId(carrierService.getCarrier().getId())
                .code(carrierService.getCode())
                .name(carrierService.getName())
                .description(carrierService.getDescription())
                .isNormal(carrierService.getIsNormal())
                .isReturn(carrierService.getIsReturn())
                .isPickup(carrierService.getIsPickup())
                .createdAt(carrierService.getCreatedAt())
                .updatedAt(carrierService.getUpdatedAt())
                .deletedAt(carrierService.getDeletedAt())
                .build();
    }
}
