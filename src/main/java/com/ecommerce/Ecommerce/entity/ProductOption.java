package com.ecommerce.Ecommerce.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class ProductOption extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID )
    private String id;

    private String code;

    private String name;

    @ManyToOne
    @JoinColumn(name = "group_id")
    @JsonIgnore
    private ProductOptionGroup group;

}
