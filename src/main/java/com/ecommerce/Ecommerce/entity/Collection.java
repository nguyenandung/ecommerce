package com.ecommerce.Ecommerce.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIgnoreType;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "collections")
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Collection extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String name;

    @Column(unique = true)
    private String Slug;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    @JsonIgnore
    private Collection parent;

    @OneToMany(mappedBy = "parent")
    @JsonIgnore
    private List<Collection> children = new ArrayList<>();

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "collection_product_variants", joinColumns = @JoinColumn(name = "collection_id"), inverseJoinColumns = @JoinColumn(name = "product_variant_id"))
    private List<ProductVariant> productVariants = new ArrayList<>();

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "collection_products", joinColumns = @JoinColumn(name = "collection_id"), inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Product> products = new ArrayList<>();

}
