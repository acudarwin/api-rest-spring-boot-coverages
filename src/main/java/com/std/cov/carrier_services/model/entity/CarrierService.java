package com.std.cov.carrier_services.model.entity;

import com.std.cov.carriers.model.entity.Carrier;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "carrier_services")
public class CarrierService implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "carrier_id", nullable = false)
    private Carrier carrier;

    @Column(name = "code", nullable = false)
    private String code;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = true)
    private String description;

    @Column(name = "is_normal", nullable = false)
    private Boolean isNormal;

    @Column(name = "is_return", nullable = false)
    private Boolean isReturn;

    @Column(name = "is_pickup", nullable = false)
    private Boolean isPickup;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "update_at")
    private LocalDateTime updatedAt;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    //@ManyToOne(fetch = FetchType.LAZY)
    //@JoinColumn(name = "carrier_id", referencedColumnName = "id")
    //private Carrier carrier;
}
