package com.ecommerce.Ecommerce.controller;


import com.ecommerce.Ecommerce.dto.CreateFacetDto;
import com.ecommerce.Ecommerce.dto.CreateFacetValueDto;
import com.ecommerce.Ecommerce.entity.Facet;
import com.ecommerce.Ecommerce.entity.FacetValue;
import com.ecommerce.Ecommerce.service.FacetService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/facets")
@Tag(name = "Facet", description = "Facet")
public class FacetController {
    private final FacetService facetService;

    @GetMapping
    @Operation(summary = "Get all facets")
    public List<Facet> getAllFacets() {
        return facetService.getAllFacets();
    }

    @PostMapping
    @Operation(summary = "Create a new facet")
    public Facet createFacet(@RequestBody CreateFacetDto dto) {
        return facetService.createFacet(dto.code, dto.name);
    }

    @PostMapping("/{id}/value")
    @Operation(summary = "Create a new facet value")
    public FacetValue createFacetValue(@PathVariable String id,@RequestBody CreateFacetValueDto dto) {
        return facetService.createFacetValue(UUID.fromString(id),dto.code, dto.name);
    }
}

