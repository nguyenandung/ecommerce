package com.ecommerce.Ecommerce.dto;

import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@RequiredArgsConstructor
public class VariantSummaryDto {
    public String id;
    public String sku;
    public BigDecimal price;
    public int stockLevel;

    public VariantSummaryDto(String id, String sku, BigDecimal price, int stockLevel) {
    }
}
