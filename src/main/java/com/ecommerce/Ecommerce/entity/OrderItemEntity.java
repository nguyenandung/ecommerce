package com.ecommerce.Ecommerce.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "order_items", indexes = {

        @Index(columnList = "orderId"),
        @Index(columnList = "shopId"),
        @Index(columnList = "productId"),
        @Index(columnList = "variantId")
}

)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemEntity extends BaseEntity{
    @ManyToOne
    @JoinColumn(name = "order_id")
    @JsonIgnore
    private OrderEntity order;

    private String productId;

    private String variantId;

    private String currency;

    private BigDecimal price;

    private int quantity;





}
