package com.ecommerce.Ecommerce.entity;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BankAccountEntity extends BaseEntity {
    private String ownerId ;

    private String bankAccount;

    private String bankCode;

    private String bankName;

    private String bankLoginId;

    private String bankPassword;

    private BigDecimal balance;

}
