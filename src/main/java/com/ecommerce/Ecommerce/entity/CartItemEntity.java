package com.ecommerce.Ecommerce.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@Entity
@Table(name = "carts")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItemEntity extends  BaseEntity{
    @ManyToOne
    @JoinColumn(name="cart_id")
    @JsonIgnore
    private CartEntity cart;

    @ManyToOne
    @JoinColumn(name="product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name="variant_id")
    private ProductVariant variant;

    @ManyToOne
    @JoinColumn(name="shop_id")
    private ShopEntity shop;

    private String currency;

    private BigDecimal price;

    private int quantity;


}
