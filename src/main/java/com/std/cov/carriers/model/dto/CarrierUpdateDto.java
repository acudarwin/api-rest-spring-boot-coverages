package com.std.cov.carriers.model.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@ToString
@Builder
public class CarrierUpdateDto implements Serializable {

    private Integer id;

    private String code;

    private String name;

    private String carrierManager;

    private Boolean cancelDelivery;

    private LocalDateTime updatedAt;

    private LocalDateTime deletedAt;
}
