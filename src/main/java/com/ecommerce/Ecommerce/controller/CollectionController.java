package com.ecommerce.Ecommerce.controller;


import com.ecommerce.Ecommerce.dto.CreateCollectionDto;
import com.ecommerce.Ecommerce.entity.Collection;
import com.ecommerce.Ecommerce.service.CollectionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/collections")
@Tag(name = "Collection", description = "Collection API")
public class CollectionController {
    private final CollectionService collectionService;

    @GetMapping
    @Operation(summary = "Get all Collections")
    public List<Collection> getCollections() {
        return collectionService.list();
    }

    @PostMapping()
    @Operation(summary = "Create a new Collection")
    public Collection createCollection(@RequestBody CreateCollectionDto dto) {
        return collectionService.createCollection(dto.name, dto.slug, dto.parentId);
    }

    @PostMapping("/{id}/variants/{variantId}")
    @Operation(summary = "Add a variant to a collection")
    public void addVariant(@PathVariable String id , @PathVariable String variantId){
         collectionService.addVariantToCollection(UUID.fromString(id), UUID.fromString(variantId));
    }

    @PostMapping("/{id}/facets/{facetValueId}")
    @Operation(summary = "Add variants by facet value")
    public void addByFacet(@PathVariable String id , @PathVariable String facetValueId){
        collectionService.addVariantsByFacetValue(UUID.fromString(id), UUID.fromString(facetValueId));
    }

}
