package com.std.cov.carriers.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@ToString
@Builder
public class CarrierDto implements Serializable {

    private Integer id;

    @NotNull(message = "code is required")
    @NotBlank(message = "Code is required")
    private String code;

    @NotNull(message = "name is required")
    @NotBlank(message = "name is required")
    private String name;

    @NotNull(message = "carrier_manager is required")
    @NotBlank(message = "carrier_manager is required")
    private String carrierManager;

    @NotNull(message = "cancel_delivery is required")
    private Boolean cancelDelivery;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private LocalDateTime deletedAt;

}
