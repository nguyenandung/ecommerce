package com.ecommerce.Ecommerce.controller;


import com.ecommerce.Ecommerce.dto.CollectionProductsResponseDto;
import com.ecommerce.Ecommerce.dto.CreateCollectionDto;
import com.ecommerce.Ecommerce.dto.PageResponse;
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

//    @GetMapping
//    @Operation(summary = "Get all Collections")
//    public List<Collection> getCollections() {
//        return collectionService.list();
//    }

    @GetMapping
    @Operation(summary = "Get all collections with pagiation")
    public PageResponse<Collection> list(@RequestParam(defaultValue = "0") int page ,
                                         @RequestParam(defaultValue = "10") int size,
                                         @RequestParam(defaultValue = "name") String sortBy,
                                         @RequestParam(defaultValue = "asc") String sortDir
                                         ){
        return collectionService.listWithPagination(page,size, String.valueOf(sortBy), String.valueOf(sortDir));
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

    @PostMapping("/{id}/products/{productId}")
    @Operation(summary = "Add a product to a collection")
   public void addByProduct(@PathVariable String id , @PathVariable String productId){
        collectionService.addProductToCollection(UUID.fromString(id), UUID.fromString(productId));
   }

    @GetMapping("/{id}/products")
    @Operation(summary = "Get products in a collection")
    public CollectionProductsResponseDto getProductsInCollection(@PathVariable String id,
                                                                 @RequestParam(defaultValue = "0") int page,
                                                                 @RequestParam(defaultValue = "20") int size,
                                                                 @RequestParam(defaultValue = "name") String sortBy,
                                                                 @RequestParam(defaultValue = "asc") String sortDir) {
        UUID collectionId = UUID.fromString(id);
        return collectionService.getCollectionProducts(collectionId, page, size, sortBy, sortDir);
    }

}
