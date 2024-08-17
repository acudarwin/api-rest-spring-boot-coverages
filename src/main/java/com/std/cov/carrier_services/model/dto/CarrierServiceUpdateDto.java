package com.std.cov.carrier_services.model.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@ToString
@Builder
public class CarrierServiceUpdateDto implements Serializable {

    private Integer id;

    private Integer carrierId;

    private String code;

    private String name;

    private String description;

    private Boolean isNormal;

    private Boolean isReturn;

    private Boolean isPickup;

    private LocalDateTime updatedAt;

    private LocalDateTime deletedAt;
}
