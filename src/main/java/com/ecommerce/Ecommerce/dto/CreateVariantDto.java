package com.ecommerce.Ecommerce.dto;

import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public class CreateVariantDto {
    public String sku;
     public BigDecimal price;
    public String currency;
    public int stock;
    public List<UUID> optionIds;
}
