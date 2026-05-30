package com.ecommerce.Ecommerce.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItemDto {
   public String productId;
   public String variantId;
   public String shopId;
   public BigDecimal price;
   public String currency;
   public int quantity;
}
