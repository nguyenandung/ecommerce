package com.ecommerce.Ecommerce.entity;


import com.ecommerce.Ecommerce.enums.OrderStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name ="order_history", indexes= {
        @Index(columnList = "order_id")
})
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderHistoryEntity extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "order_id")
    @JsonIgnore
    private OrderEntity order;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "TEXT")
    private OrderStatus status;

    private String note;
}
