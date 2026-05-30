package com.ecommerce.Ecommerce.controller;

import com.ecommerce.Ecommerce.dto.CartDto;
import com.ecommerce.Ecommerce.service.CartService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/carts")
@Tag(name = "Cart", description = "Cart management APIs")
@AllArgsConstructor
public class CartController {
    private final CartService cartService;

    @PostMapping
    @Operation(summary = "Add to cart")
    public void addToCart(@RequestBody CartDto dto) {
        cartService.addToCart(dto);
    }
}
