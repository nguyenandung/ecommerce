package com.ecommerce.Ecommerce.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Facet extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String name;

    @Column(unique = true)
    private String code;

    @OneToMany(mappedBy = "facet", cascade = CascadeType.ALL)
    private List<FacetValue> values;
}
