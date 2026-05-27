package com.ecommerce.Ecommerce.service;

import com.ecommerce.Ecommerce.repository.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
    public void addToCart(){}
}
