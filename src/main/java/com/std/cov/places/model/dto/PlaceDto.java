package com.std.cov.places.model.dto;

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
public class PlaceDto implements Serializable {

    private Integer id;

    @NotNull(message = "name_from is required")
    @NotBlank(message = "name_to is required")
    private String nameFrom;

    @NotNull(message = "name_to is required")
    @NotBlank(message = "name_to is required")
    private String nameTo;

    @NotNull(message = "customization is required")
    private String customization;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private LocalDateTime deletedAt;

}
