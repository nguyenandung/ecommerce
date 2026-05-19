package com.ecommerce.Ecommerce.controller;

import com.ecommerce.Ecommerce.dto.CreateProductDto;
import com.ecommerce.Ecommerce.dto.CreateVariantDto;
import com.ecommerce.Ecommerce.entity.Product;
import com.ecommerce.Ecommerce.entity.ProductVariant;
import com.ecommerce.Ecommerce.service.ProductService;
import com.ecommerce.Ecommerce.service.ProductVariantService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
@Tag(name = "Product", description = "Product management APIs")
public class ProductController {
    private final ProductService productService;
    private final ProductVariantService variantService;

    @PostMapping
    @Operation(summary = "Create a new Product")
    public Product createProduct(@RequestBody CreateProductDto dtos){
        return productService.createProduct(dtos);
    }


    @GetMapping
    @Operation(summary = "Get all products")
    public List<Product> getAllProducts(){
        return productService.getAllProducts();
    }


    @PostMapping("/{id}/facets/{facetValueId}")
    public Product addFacetValue(@PathVariable UUID id, @PathVariable UUID facetValueId) {
        return productService.addFacetValueToProduct(id, facetValueId);
    }

       @PostMapping("/{productId}/variants")
    @Operation(summary = "Create a variant for a product")
    public ProductVariant createVariant(@PathVariable String productId, @RequestBody CreateVariantDto dto) {
        return variantService.createVariant(UUID.fromString(productId), dto.sku, dto.price, dto.currency, dto.stock, dto.optionIds);
    }

}

