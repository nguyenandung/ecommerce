package com.ecommerce.Ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateListingDto {
    public String shopId;

    public String productId;

    public String variantId;

    public BigDecimal price;

    public String currency;
}
