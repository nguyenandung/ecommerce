package com.ecommerce.Ecommerce.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "carts", indexes = {
        @Index(columnList = "customerId")
})
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartEntity extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "customer_id")
    @JsonIgnore
    private CustomerEntity customer;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItemEntity> cartItems = new ArrayList<>();

}
