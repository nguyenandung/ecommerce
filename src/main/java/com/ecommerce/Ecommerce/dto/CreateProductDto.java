package com.ecommerce.Ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateProductDto {
    public String name;

    public String description;

    public String slug;

    public List<String> collectionIds;

    public List<String> facetValueIds;

}
