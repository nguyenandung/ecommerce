package com.ecommerce.Ecommerce.controller;

import com.ecommerce.Ecommerce.dto.CreateListingDto;
import com.ecommerce.Ecommerce.dto.CreateShopDto;
import com.ecommerce.Ecommerce.entity.ShopEntity;
import com.ecommerce.Ecommerce.service.ShopService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/shops")
@Tag(name = "Shop", description = "Shop management APIs")
@AllArgsConstructor
public class ShopController {
    private final ShopService shopService;

    @GetMapping
    @Operation(summary = "Get all shops")
    public List<ShopEntity> list(@RequestParam Map<String, Object> params){
        return shopService.list(params);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get shop")
    public ShopEntity get(@PathVariable String id){
       return shopService.get(id);
    }
    @PostMapping("/create-listing")
    @Operation(summary = "Create listing")
    public void createListing(@RequestBody List<CreateListingDto> dto) {
        shopService.createListing(dto);
    }


    @PostMapping
    @Operation(summary = "Create a new shop")
    public void create(@RequestBody CreateShopDto dto) {
         shopService.create(dto);
    }



}
