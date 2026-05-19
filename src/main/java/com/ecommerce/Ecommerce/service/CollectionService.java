package com.ecommerce.Ecommerce.service;


import com.ecommerce.Ecommerce.entity.Collection;
import com.ecommerce.Ecommerce.entity.FacetValue;
import com.ecommerce.Ecommerce.entity.ProductVariant;
import com.ecommerce.Ecommerce.repository.CollectionRepository;
import com.ecommerce.Ecommerce.repository.FacetRepository;
import com.ecommerce.Ecommerce.repository.FacetValueRepository;
import com.ecommerce.Ecommerce.repository.ProductVariantRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class CollectionService {
    private final CollectionRepository collectionRepository;
    private final ProductVariantRepository productVariantRepository;
    private final FacetRepository facetRepository;
    private final FacetValueRepository facetValueRepository;


    public List<Collection> list() {
        return collectionRepository.findAll();
    }

    public Collection createCollection(String name, String slug , String parentId) {
        Collection collection = new Collection();
        collection.setName(name);
        collection.setSlug(slug);

        if(parentId != null) {
            Collection parent = collectionRepository.findById(parentId).orElseThrow(
                    () -> new RuntimeException("Parent collection not found")
            );
            collection.setParent(parent);
        }
        return collectionRepository.save(collection);
    }

    public void addVariantToCollection(UUID collectionId, UUID variantId){
        Collection collection = collectionRepository.findById(collectionId.toString()).orElseThrow(
                () -> new RuntimeException("Collection not found")
        );
        ProductVariant variant = productVariantRepository.findById(variantId.toString()).orElseThrow(
                () -> new RuntimeException("Product variant not found")
        );

        if(!collection.getProductVariants().contains(variant)) {
            collection.getProductVariants().add(variant);
            collectionRepository.save(collection);
        }
    }

  public void addVariantsByFacetValue(UUID collectionId, UUID facetValueId){
        Collection collection = collectionRepository.findById(collectionId.toString()).orElseThrow(
                () -> new RuntimeException("Collection not found")

        );

      FacetValue facetValue = facetValueRepository.findById(facetValueId.toString()).orElseThrow(
              () -> new RuntimeException("Facet value not found")
      );

       List<ProductVariant> variants = facetValue.getVariants();

       if(facetValue.getProducts() != null){
           facetValue.getProducts().forEach(product -> {
               if(product.getVariants() != null){
                   variants.addAll(product.getVariants());
               }
           });
       }

       for(ProductVariant v : variants) {
            if(!collection.getProductVariants().contains(v)) {
                collection.getProductVariants().add(v);
            }
       }

  }




}

