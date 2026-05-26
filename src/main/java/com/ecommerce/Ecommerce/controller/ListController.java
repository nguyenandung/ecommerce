package com.ecommerce.Ecommerce.controller;

import com.ecommerce.Ecommerce.entity.ListingEntity;
import com.ecommerce.Ecommerce.service.ListingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/listings")
@Tag(name = "Listing", description = "Listing management APIs")
@AllArgsConstructor
public class ListController {
    private final ListingService listingService;

    @GetMapping
    @Operation(summary = "Get all listing")
    public List<ListingEntity> list(@RequestParam
            Map<String, Object> params){
            return listingService.list(params);
    }
}
