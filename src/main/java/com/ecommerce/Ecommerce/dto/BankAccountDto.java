package com.ecommerce.Ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
public class BankAccountDto {
    public String bankAccount;

    public String bankCode;

    public String bankName;

    public String bankLoginId;

    public String bankPassword;

    public BigDecimal balance;

    public String ownerId;

    public BankAccountDto(String shopId, String bankAccount, String bankCode, String bankName, String bankLoginId, String bankPassword, BigDecimal zero) {
    }
}
