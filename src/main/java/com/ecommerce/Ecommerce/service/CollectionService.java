package com.ecommerce.Ecommerce.service;


import com.ecommerce.Ecommerce.dto.CollectionProductDto;
import com.ecommerce.Ecommerce.dto.CollectionProductsResponseDto;
import com.ecommerce.Ecommerce.dto.VariantSummaryDto;
import com.ecommerce.Ecommerce.entity.Collection;
import com.ecommerce.Ecommerce.entity.FacetValue;
import com.ecommerce.Ecommerce.entity.Product;
import com.ecommerce.Ecommerce.entity.ProductVariant;
import com.ecommerce.Ecommerce.repository.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Transactional
@RequiredArgsConstructor
public class CollectionService  implements BaseService<Collection,String >{
    private final CollectionRepository collectionRepository;
    private final ProductVariantRepository productVariantRepository;
    private final ProductRepository productRepository;
    private final FacetValueRepository facetValueRepository;


    @Override
    public JpaRepository<Collection, String> getRepository(){
        return collectionRepository;
    }

//    public List<Collection> list() {
//        return collectionRepository.findAll();
//    }



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

    public   void addProductToCollection(UUID collectionId, UUID productId) {
        Collection collection = collectionRepository.findById(collectionId.toString()).orElseThrow(
                () -> new RuntimeException("Collection not found")

        );

        Product product = productRepository.findById(productId.toString()).orElseThrow(
                () -> new RuntimeException("Product not found")
        );

        if(!collection.getProducts().contains(product)){
            collection.getProducts().add(product);
            collectionRepository.save(collection);
        }
    }

   public CollectionProductsResponseDto getCollectionProducts(UUID collectionId, int page, int size,
                                                              String sortBy, String sortDir){
       if(page < 0){
           page = 0;
       }

       if(size < 0){
           size = 20;
       }

       Optional<Collection> optionalCollection = collectionRepository.findById(collectionId.toString());

       if(optionalCollection.isEmpty()){
           return new CollectionProductsResponseDto(collectionId.toString(),"", "", new ArrayList<>(), 0L, page,
                   size );
       }

       Collection collection = optionalCollection.get();

       Map<String, Product>  productById =  new HashMap<>();
       Map<String, ArrayList<ProductVariant>> variantsByProductId = new HashMap<>();

       for(ProductVariant variant : collection.getProductVariants()){
           Product product = variant.getProduct();
           if(product == null){
               continue;
           }

           productById.putIfAbsent(product.getId(), product);
           variantsByProductId.computeIfAbsent(product.getId(), k -> new ArrayList<>()).add(variant);
       }

       for(Product product : collection.getProducts()){
           productById.putIfAbsent(product.getId(), product);
           List<ProductVariant> allProductVariants = product.getVariants() != null ? product.getVariants() : new ArrayList<>();

           variantsByProductId.put(product.getId(), new ArrayList<>(allProductVariants));
       }


       List<CollectionProductDto> productDtos = new ArrayList<>();
       for (Map.Entry<String, Product> entry : productById.entrySet()) {
           String productId = entry.getKey();
           Product product = entry.getValue();
           List<ProductVariant> variants = variantsByProductId.getOrDefault(productId, new ArrayList<>());

           List<VariantSummaryDto> variantDtos = new ArrayList<>();
           for (ProductVariant variant : variants) {
               variantDtos.add(new VariantSummaryDto(variant.getId(), variant.getSku(), variant.getPrice(),
                       variant.getStockLevel()));
           }

           CollectionProductDto productDto = new CollectionProductDto(product.getId(), product.getName(),
                   product.getSlug(), product.getDescription(), product.isEnabled(), variantDtos);
           productDtos.add(productDto);
       }

       // Sorting
       Comparator<CollectionProductDto> comparator;
//       if ("slug".equalsIgnoreCase(sortBy)) {
//           comparator = Comparator.comparing(dto -> dto.slug, String.CASE_INSENSITIVE_ORDER);
//       } else { // default name
//           comparator = Comparator.comparing(dto-> dto.name, String.CASE_INSENSITIVE_ORDER);
//       }

       if ("slug".equalsIgnoreCase(sortBy)) {
           comparator = Comparator.comparing(
                   CollectionProductDto::getSlug,
                   Comparator.nullsLast(String.CASE_INSENSITIVE_ORDER)
           );
       } else { // default name
           comparator = Comparator.comparing(
                   CollectionProductDto::getName,
                   Comparator.nullsLast(String.CASE_INSENSITIVE_ORDER)
           );
       }

       if ("desc".equalsIgnoreCase(sortDir)) {
           comparator = comparator.reversed();
       }
       productDtos.sort(comparator);

       long totalItems = productDtos.size();
       int fromIndex = page * size;
       int toIndex = Math.min(fromIndex + size, productDtos.size());
       List<CollectionProductDto> pagedProducts;
       if (fromIndex >= productDtos.size()) {
           pagedProducts = new ArrayList<>();
       } else {
           pagedProducts = productDtos.subList(fromIndex, toIndex);
       }

       return new CollectionProductsResponseDto(collection.getId(), collection.getName(), collection.getSlug(),
               pagedProducts, totalItems, page, size);

   }



}

