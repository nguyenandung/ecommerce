package com.ecommerce.Ecommerce.entity;

import com.ecommerce.Ecommerce.enums.CustomerStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "customers",indexes = {
        @Index(columnList = "userId")
})
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerEntity extends BaseEntity{
    private String userId;

    private int totalOrders = 0;

   private BigDecimal totalSpent = BigDecimal.ZERO;

   @Enumerated(EnumType.STRING)
   @Column(columnDefinition = "TEXT")
   private CustomerStatus status = CustomerStatus.ACTIVE;

}
