package com.std.cov.carrier_services.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@ToString
@Builder
public class CarrierServiceDto implements Serializable {

    private Integer id;

    private Integer carrierId;

    @NotNull(message = "code is required")
    @NotBlank(message = "code is required")
    private String code;

    @NotNull(message = "name is required")
    @NotBlank(message = "name is required")
    private String name;

    @NotNull(message = "description is required")
    @NotBlank(message = "description is required")
    private String description;

    @NotNull(message = "is_normal is required")
    private Boolean isNormal;

    @NotNull(message = "is_return is required")
    private Boolean isReturn;

    @NotNull(message = "is_pickup is required")
    private Boolean isPickup;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private LocalDateTime deletedAt;

}
