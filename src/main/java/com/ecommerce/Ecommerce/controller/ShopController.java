package com.ecommerce.Ecommerce.controller;

import com.ecommerce.Ecommerce.dto.CreateShopDto;
import com.ecommerce.Ecommerce.service.ShopService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/shops")
@Tag(name = "Shop", description = "Shop management APIs")
@AllArgsConstructor
public class ShopController {
    private final ShopService shopService;

    @PostMapping
    @Operation(summary = "Create a new shop")
    public void create(@RequestBody CreateShopDto dto) {
         shopService.create(dto);
    }
}
