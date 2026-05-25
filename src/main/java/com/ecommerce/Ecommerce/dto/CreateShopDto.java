package com.ecommerce.Ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
public class CreateShopDto {
    public String name;

    public String phoneNumber;

   public String email;

   public List<BankAccountDto> bankAccounts;
}
