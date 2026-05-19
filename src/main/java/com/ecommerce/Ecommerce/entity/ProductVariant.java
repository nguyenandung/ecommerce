package com.ecommerce.Ecommerce.entity;

import com.ecommerce.Ecommerce.enums.OrderStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class ProductVariant extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(unique = true)
    private String sku;

    private BigDecimal price;

    private String currency;

    private int stockLevel;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    @JsonIgnore
    private Product product;

    @ManyToMany
    @JoinTable(name = "variant_options", joinColumns = @JoinColumn(name = "variant_id"), inverseJoinColumns = @JoinColumn(name = "option_id"))
    private List<ProductOption> options = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "variant_facet_values", joinColumns = @JoinColumn(name = "variant_id"), inverseJoinColumns = @JoinColumn(name = "facet_value_id"))
    private List<FacetValue> facetValues = new ArrayList<>();

    private OrderStatus orderStatus;

    private boolean customFieldRequired;
}
