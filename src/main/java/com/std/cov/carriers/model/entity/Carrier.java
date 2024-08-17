package com.std.cov.carriers.model.entity;

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
@Table(name = "carriers")
public class Carrier implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "code", updatable = false, nullable = false)
    private String code;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "carrier_manager", nullable = false)
    private String carrierManager;

    @Column(name = "cancel_delivery", nullable = false)
    private Boolean cancelDelivery = false;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "update_at")
    private LocalDateTime updatedAt;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    //@OneToMany(mappedBy = "carrier", cascade = CascadeType.ALL)
    //private CarrierService carrierService;

}
