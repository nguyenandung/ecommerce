package com.ecommerce.Ecommerce.entity;

import com.ecommerce.Ecommerce.enums.OrderStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders", indexes = {
        @Index(columnList = "code"),
        @Index(columnList = "customerId"),
        @Index(columnList = "shopId")
})
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderEntity extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "customer_id")
    @JsonIgnore
    private CustomerEntity customer;

    @ManyToOne
    @JoinColumn(name = "shop_id")
    @JsonIgnore
    private ShopEntity shop;

    private BigDecimal totalAmount;

    private String currency;

    private String node;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "TEXT")
    private OrderStatus status = OrderStatus.CREATED;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItemEntity> orderItems = new ArrayList<>();

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderHistoryEntity> orderHistory = new ArrayList<>();

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderPaymentEntity> orderPayments = new ArrayList<>();

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "payment_id")
        @JsonIgnore
    private PaymentEntity payment;
}
