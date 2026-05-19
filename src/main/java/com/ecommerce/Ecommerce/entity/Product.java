package com.ecommerce.Ecommerce.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "products")
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Product extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id ;

    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(unique = true)
    private String slug;

    private boolean enabled = true;

    @JsonIgnore
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductVariant> variants = new ArrayList<>();

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "product_facet_values", joinColumns = @JoinColumn(name = "product_id"), inverseJoinColumns = @JoinColumn(name = "facet_value_id"))
    private List<FacetValue> facetValues = new ArrayList<>();

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "collection_products", joinColumns = @JoinColumn(name = "product_id"), inverseJoinColumns = @JoinColumn(name = "collection_id"))
    private List<Collection> collections = new ArrayList<>();





}
