package com.ecommerce.Ecommerce.service;

import com.ecommerce.Ecommerce.entity.Product;
import com.ecommerce.Ecommerce.entity.ProductOption;
import com.ecommerce.Ecommerce.entity.ProductVariant;
import com.ecommerce.Ecommerce.repository.ProductOptionRepository;
import com.ecommerce.Ecommerce.repository.ProductRepository;
import com.ecommerce.Ecommerce.repository.ProductVariantRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductVariantService {
    private final ProductRepository productRepository;
    private final ProductVariantRepository productVariantRepository;
    private final ProductOptionRepository productOptionRepository;

    public ProductVariant createVariant(UUID productId, String sku
     , BigDecimal price , String currency ,int stock, List<UUID> optionIds
    ) {
        Product product = productRepository.findById(productId.toString()).orElseThrow(
                () -> new RuntimeException(" Product not found")
        );

        ProductVariant variant = new ProductVariant();
        variant.setProduct(product);
        variant.setSku(sku);
        variant.setPrice(price);
        variant.setStockLevel(stock);

        if(optionIds != null && !optionIds.isEmpty()){
           List<ProductOption> options = productOptionRepository.findAllById(optionIds.stream().map(UUID::toString).toList());
           variant.setOptions(options);


        }
        return productVariantRepository.save(variant);


    }


}
