package com.std.cov.coverages.model.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@ToString
@Builder
public class CoverageDto implements Serializable {

    private Integer id;

    @NotNull(message = "carrier_id is required")
    private Integer carrierId;

    private Integer carrierServiceId;

    private Integer placeIdFrom;

    private Integer placeIdTo;

    private Boolean isBlocked;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private LocalDateTime deletedAt;
}
