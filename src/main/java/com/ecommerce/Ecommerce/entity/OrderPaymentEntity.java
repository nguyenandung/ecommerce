package com.ecommerce.Ecommerce.entity;

import com.ecommerce.Ecommerce.enums.PaymentStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "order_payments",indexes = {
        @Index(columnList = "orderId"),
        @Index(columnList = "paymentId"),
        @Index(columnList = "status")
})
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderPaymentEntity extends BaseEntity {
     private BigDecimal amount;

     private String currency;

     @Enumerated(EnumType.STRING)
     @Column(columnDefinition = "TEXT")
     private PaymentStatus status = PaymentStatus.PENDING;

     @ManyToOne(fetch = FetchType.LAZY)
     @JoinColumn(name = "order_id")
     @JsonIgnore
     private OrderEntity order;

     @ManyToOne
    @JoinColumn(name = "payment_id")
    @JsonIgnore
    private OrderPaymentEntity payment;
}
