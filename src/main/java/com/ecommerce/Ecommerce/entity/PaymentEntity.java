package com.ecommerce.Ecommerce.entity;

import com.ecommerce.Ecommerce.enums.PaymentMethod;
import com.ecommerce.Ecommerce.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "payments", indexes = {
        @Index(columnList = "orderId"),
})
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentEntity extends BaseEntity {
    private String code;

    private BigDecimal totalAmount;

    private String currency;

    @Enumerated
    @Column(columnDefinition = "TEXT")
    private PaymentStatus status;

    @Enumerated
    @Column(columnDefinition = "TEXT")
    private PaymentMethod paymentMethod = PaymentMethod.BANK_TRANSFER;

     private String paymentGwTxId;

     @OneToMany(mappedBy = "payment", cascade = CascadeType.ALL, orphanRemoval = true)
     private List<OrderEntity> orders = new ArrayList<>();
}
