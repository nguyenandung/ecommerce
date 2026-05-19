package com.ecommerce.Ecommerce.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class ProductOptionGroup extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(unique = true)
    private String code;

    private String name;

    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL)
    private List<ProductOption> options;


}
