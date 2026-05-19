package com.ecommerce.Ecommerce.service;


import com.ecommerce.Ecommerce.dto.CreateProductDto;
import com.ecommerce.Ecommerce.entity.Collection;
import com.ecommerce.Ecommerce.entity.FacetValue;
import com.ecommerce.Ecommerce.entity.Product;
import com.ecommerce.Ecommerce.repository.CollectionRepository;
import com.ecommerce.Ecommerce.repository.FacetValueRepository;
import com.ecommerce.Ecommerce.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final CollectionRepository collectionRepository;
    private final FacetValueRepository facetValueRepository;

    public Product createProduct(CreateProductDto dto) {
        Product product = new Product();
        product.setName(dto.name);
        product.setSlug(dto.slug);
        product.setDescription(dto.description);
        if (dto.collectionIds != null && !dto.collectionIds.isEmpty()) {
            List<Collection> collections = collectionRepository.findAllById(dto.collectionIds);
            product.setCollections(collections);
        }
        if (dto.facetValueIds != null && !dto.facetValueIds.isEmpty()) {
            List<FacetValue> facetValues = facetValueRepository.findAllById(dto.facetValueIds);
            product.setFacetValues(facetValues);
        }

      return  productRepository.save(product);

    }

    public List<Product>  getAllProducts() {
        return productRepository.findAll();
    }


    public Product addFacetValueToProduct(UUID productId , UUID facetValueId) {
        Product product = productRepository.findById(productId.toString()).orElseThrow(
                () -> new RuntimeException("Product not found")
        );
        FacetValue facetValue = facetValueRepository.findById(facetValueId.toString()).orElseThrow(
                () -> new RuntimeException("FacetValue not found")
        );

        product.getFacetValues().add(facetValue);
        return productRepository.save(product);
    }

}
