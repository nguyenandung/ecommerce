package com.ecommerce.Ecommerce.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class FacetValue extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String code;

    private String name;

    @ManyToOne
    @JoinColumn(name = "facet_id")
    @JsonIgnore
    private Facet facet;

    @ManyToMany(mappedBy = "facetValues")
    @JsonIgnore
    private List<Product> products;

    @ManyToMany(mappedBy = "facetValues")
    @JsonIgnore
    private List<ProductVariant> variants;

}
