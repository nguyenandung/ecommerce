package com.ecommerce.Ecommerce.service;

import com.ecommerce.Ecommerce.dto.CartDto;
import com.ecommerce.Ecommerce.dto.CartItemDto;
import com.ecommerce.Ecommerce.entity.*;
import com.ecommerce.Ecommerce.repository.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;
    private final ShopRepository shopRepository;
    private  final ProductVariantRepository variantRepository;
    private final CustomerRepository customerRepository;


    @Transactional
    public void addToCart(CartDto dto){
        CartEntity cart = new CartEntity();
        if(dto.getId() == null){

        }else {
            cart.setId(dto.getId());
        }

        cartRepository.save(cart);
        // Sync cart items.
        List<String> productIds = new ArrayList<>();
        List<String> variantIds = new ArrayList<>();
        List<String> shopIds = new ArrayList<>();
        for(CartItemDto item : dto.cartItems){
            if(!productIds.contains(item.getProductId())){
                productIds.add(item.getProductId());
            }
            if(!variantIds.contains(item.getVariantId())){
                variantIds.add(item.getVariantId());
            }
            if(!shopIds.contains(item.getShopId())){
                shopIds.add(item.getShopId());
            }
        }

        List<Product> products = productRepository.findAllById(productIds);
        List<ProductVariant> variants = variantRepository.findAllById(variantIds);
        List<ShopEntity> shops = shopRepository.findAllById(shopIds);

        Map<String, Product> productMap = new HashMap<>();
        for(Product p : products) {
            productMap.put(p.getId(), p);
        }

        Map<String, ProductVariant> variantMap = new HashMap<>();
        for(ProductVariant v : variants) {
            variantMap.put(v.getId(), v);
        }

        Map<String, ShopEntity> shopMap = new HashMap<>();
        for(ShopEntity s : shops) {
            shopMap.put(s.getId(), s);
        }
        List<CartItemEntity> cartItems = new ArrayList<>();
        for(CartItemDto itemDto : dto.cartItems){
            Product product = productMap.get(itemDto.getProductId());
            ProductVariant variant = variantMap.get(itemDto.getVariantId());
            ShopEntity shop = shopMap.get(itemDto.getShopId());
            if(product == null) {
                throw new RuntimeException("Product not found: " + itemDto.getProductId());

            }
            if(variant == null) {
                throw new RuntimeException("Variant not found: " + itemDto.getVariantId());
            }
            if(shop == null) {
                throw new RuntimeException("Shop not found: " + itemDto.getShopId());
            }
            cartItems.add(new CartItemEntity(cart, product, variant, shop, itemDto.getCurrency(), itemDto.getPrice(), itemDto.getQuantity()));
        }
        cartItemRepository.saveAll(cartItems);


    }
}
