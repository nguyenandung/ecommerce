package com.ecommerce.Ecommerce.dto;

import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class CreateProductDto {
    public String name;

    public String description;

    public String slug;

    public List<String> collectionIds;

    public List<String> facetValueIds;

}
