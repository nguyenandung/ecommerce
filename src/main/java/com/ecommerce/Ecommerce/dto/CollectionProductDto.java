package com.ecommerce.Ecommerce.dto;

import lombok.RequiredArgsConstructor;

import java.util.List;

public class CollectionProductDto {
    private String id;
    private String name;
    private String slug;
    private String description;
    private boolean enabled;
    private List<VariantSummaryDto> variants;

    public CollectionProductDto(String id, String name, String slug, String description,
                                boolean enabled, List<VariantSummaryDto> variants) {
        this.id = id;
        this.name = name;
        this.slug = slug;
        this.description = description;
        this.enabled = enabled;
        this.variants = variants;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public String getSlug() { return slug; }
    public String getDescription() { return description; }
    public boolean isEnabled() { return enabled; }
    public List<VariantSummaryDto> getVariants() { return variants; }
}

