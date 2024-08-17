package com.std.cov.coverages.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "coverages")
public class Coverage implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "carrier_id", nullable = false)
    private Integer carrierId;

    @Column(name = "carrier_service_id", nullable = true)
    private Integer carrierServiceId;

    @Column(name = "place_id_from", nullable = true)
    private Integer placeIdFrom;

    @Column(name = "place_id_to", nullable = true)
    private Integer placeIdTo;

    @Column(name = "is_blocked", nullable = false)
    private Boolean isBlocked;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "update_at")
    private LocalDateTime updatedAt;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

}
