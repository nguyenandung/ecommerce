package com.ecommerce.Ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CollectionProductsResponseDto {
    private String collectionId;
    private String collectionName;
    private String collectionSlug;
    private List<CollectionProductDto> products;
    private long totalItems;
    private int page;
    private int size;
}


