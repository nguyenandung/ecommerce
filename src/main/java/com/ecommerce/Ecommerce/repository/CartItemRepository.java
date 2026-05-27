package com.ecommerce.Ecommerce.repository;

import com.ecommerce.Ecommerce.entity.CartItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends JpaRepository<CartItemEntity, String> {
}
