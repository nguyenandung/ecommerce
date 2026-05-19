package com.ecommerce.Ecommerce.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.time.LocalDateTime;

@MappedSuperclass
@Data
public class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
//
//    @CreationTimestamp
//    @Column(columnDefinition = "datetime(6) default current_timestamp(6)")
//    private Instant createdAt;
//
//    @UpdateTimestamp
//    @Column(columnDefinition = "datetime(6) default current_timestamp(6) on update current_timestamp(6)")
//    private Instant updatedAt;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    private Instant deletedAt;

    private String createdBy;

    private String updatedBy;

    private String deletedBy;
}
