package com.ecommerce.Ecommerce.dto;

import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
public class CreateCollectionDto {
    public String name;
    public String slug;
    public String parentId;
}
