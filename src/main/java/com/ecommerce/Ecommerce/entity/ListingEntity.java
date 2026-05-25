package com.ecommerce.Ecommerce.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Data
@Table(name = "listings", indexes = {
        @Index(columnList = "shopId"),
        @Index(columnList = "productId"),
        @Index(columnList = "variantId")
})
@AllArgsConstructor
@NoArgsConstructor
public class ListingEntity extends BaseEntity {
    private String productId;

    private BigDecimal price;

    private String currency;

    @ManyToOne
    @JoinColumn(name = "shop_id")
    private ShopEntity  shop;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "variant_id")
    @JsonIgnore
    private ProductVariant variant;

}

