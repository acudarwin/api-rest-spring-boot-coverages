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
public class PlaceUpdateDto implements Serializable {

    private Integer id;

    private String nameFrom;

    private String nameTo;

    private String customization;

    private LocalDateTime updatedAt;

    private LocalDateTime deletedAt;
}
