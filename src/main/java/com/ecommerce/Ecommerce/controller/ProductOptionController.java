package com.ecommerce.Ecommerce.controller;


import com.ecommerce.Ecommerce.dto.CreateOptionGroupDto;
import com.ecommerce.Ecommerce.entity.ProductOption;
import com.ecommerce.Ecommerce.entity.ProductOptionGroup;
import com.ecommerce.Ecommerce.service.ProductOptionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/options")
@Tag(name = "Options", description = "Product Option API")
public class ProductOptionController {
    private final ProductOptionService productOptionService;

    @GetMapping("/group")
    @Operation(summary = "Get all groups")
    public List<ProductOptionGroup> getAllGroups(){
        return productOptionService.getAllOptionGroups();
    }

    @PostMapping("/groups")
    @Operation(summary = "Create a new group")
    public ProductOptionGroup createOptionGroup(@RequestBody CreateOptionGroupDto dto) {
        return productOptionService.createOptionGroup(dto.code,dto.name);
    }

    @PostMapping("/groups/{groupId}/values")
    @Operation(summary = "Create a new value")
    public ProductOption createValue(@PathVariable String groupId,@RequestBody CreateOptionDto dto){
        return productOptionService.createOptionValue(UUID.fromString(groupId),dto.code,dto.name);
    }
}
