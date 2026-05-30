package com.ecommerce.Ecommerce.seeder;

import com.ecommerce.Ecommerce.dto.CreateProductDto;
import com.ecommerce.Ecommerce.entity.*;
import com.ecommerce.Ecommerce.enums.RegionStatus;
import com.ecommerce.Ecommerce.repository.ProductOptionGroupRepository;
import com.ecommerce.Ecommerce.repository.ProductRepository;
import com.ecommerce.Ecommerce.repository.RegionRepository;
import com.ecommerce.Ecommerce.service.CollectionService;
import com.ecommerce.Ecommerce.service.FacetService;
import com.ecommerce.Ecommerce.service.ProductService;
import com.ecommerce.Ecommerce.service.ProductVariantService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

@Component
public class DataSeeder implements CommandLineRunner {
    private final ProductService productService;
    private final ProductVariantService productVariantService;
    private final FacetService facetService;
    private final CollectionService collectionService;

    // Repositories needed just for checking existence or setup options directly
    private final ProductRepository productRepository;
    private final ProductOptionGroupRepository optionGroupRepository;
    private final RegionRepository regionRepository;

    public DataSeeder(ProductService productService,
                      ProductVariantService productVariantService,
                      FacetService facetService,
                      CollectionService collectionService,
                      ProductRepository productRepository,
                      ProductOptionGroupRepository optionGroupRepository,
                      RegionRepository regionRepository) {
        this.productService = productService;
        this.productVariantService = productVariantService;
        this.facetService = facetService;
        this.collectionService = collectionService;
        this.productRepository = productRepository;
        this.optionGroupRepository = optionGroupRepository;
        this.regionRepository = regionRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        seedRegions();

        if (productRepository.count() > 0)
            return;

        System.out.println("Starting Data Seeding via Services...");

        // 1. Create Option Groups (Manually for now as we didn't make OptionService)
        ProductOptionGroup sizeGroup = new ProductOptionGroup();
        sizeGroup.setCode("size");
        sizeGroup.setName("Size");

        ProductOption sizeS = new ProductOption();
        sizeS.setCode("size-s");
        sizeS.setName("Small");
        sizeS.setGroup(sizeGroup);

        ProductOption sizeM = new ProductOption();
        sizeM.setCode("size-m");
        sizeM.setName("Medium");
        sizeM.setGroup(sizeGroup);

        sizeGroup.setOptions(Arrays.asList(sizeS, sizeM));
        optionGroupRepository.save(sizeGroup);

        // 2. Facets
        Facet brand = facetService.createFacet("brand", "Brand");
        FacetValue nike = facetService.createFacetValue(UUID.fromString(brand.getId()), "nike", "Nike");

        Facet category = facetService.createFacet("category", "Category");
        FacetValue electronics = facetService.createFacetValue(UUID.fromString(category.getId()), "electronics", "Electronics");

        // 3. Products
        CreateProductDto createProductDto = new CreateProductDto(
                "Nike T-Shirt",
                "nike-t-shirt",
                "Cool T-Shirt",
                List.of(),
                List.of()
        );
        Product tShirt = productService.createProduct(createProductDto);
        productService.addFacetValueToProduct(UUID.fromString(tShirt.getId()), UUID.fromString(nike.getId()));

        // 4. Variants
        ProductVariant variantS = productVariantService.createVariant(UUID.fromString(tShirt.getId()), "nike-tshirt-s",
                new BigDecimal("29.99"), "VND", 100, List.of(UUID.fromString(sizeS.getId())));
        ProductVariant variantM = productVariantService.createVariant(UUID.fromString(tShirt.getId()), "nike-tshirt-m",
                new BigDecimal("32.99"), "VND", 50, List.of(UUID.fromString(sizeM.getId())));

        // 5. Collections
        Collection promoColl = collectionService.createCollection("Summer Promo", "summer-promo", null);

        // Add product master
        collectionService.addProductToCollection(UUID.fromString(promoColl.getId()), UUID.fromString(tShirt.getId()));

        // Add variant manually
        collectionService.addVariantToCollection(UUID.fromString(promoColl.getId()), UUID.fromString(variantS.getId()));

        // Dynamic add (Simulated): Add anything with "Nike" brand to "Nike Collection"
        Collection nikeColl = collectionService.createCollection("Nike Collection", "nike-collection", null);
        // Logic inside service adds variants from product that has this facet
        collectionService.addVariantsByFacetValue(UUID.fromString(nikeColl.getId()), UUID.fromString(nike.getId()));

        System.out.println("Data Seeding Completed Successfully!");
    }

    private void seedRegions() {
        if (regionRepository.count() > 0) {
            return;
        }

        System.out.println("Seeding default regions...");

        createRegionIfNotExists("VN", "Vietnam", "VND");
        createRegionIfNotExists("ID", "Indonesia", "IDR");
        createRegionIfNotExists("TH", "Thailand", "THB");
        createRegionIfNotExists("SG", "Singapore", "SGD");
    }

    private void createRegionIfNotExists(String code, String name, String currency) {
        regionRepository.findByCode(code.toUpperCase(Locale.ROOT)).ifPresentOrElse(r -> {
            // already exists, do nothing
        }, () -> {
            Region region = new Region();
            region.setCode(code.toUpperCase(Locale.ROOT));
            region.setName(name);
            region.setCurrency(currency.toUpperCase(Locale.ROOT));
            region.setStatus(RegionStatus.ACTIVE);
            regionRepository.save(region);
        });
    }
}
