package com.ecommerce.Ecommerce.repository;

import com.ecommerce.Ecommerce.entity.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<CartEntity, String> {
}
