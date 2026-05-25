package com.ecommerce.Ecommerce.entity;


import com.ecommerce.Ecommerce.enums.ShopStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShopEntity extends BaseEntity {
    private String name;

    private String phoneNumber;

    private String email;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "TEXT")
    private ShopStatus status = ShopStatus.PENDING;

    private String ownerId;

    @Transient
    private List<BankAccountEntity> bankAccounts;
}
