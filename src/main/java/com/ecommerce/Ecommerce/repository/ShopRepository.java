package com.ecommerce.Ecommerce.repository;

import com.ecommerce.Ecommerce.entity.ShopEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopRepository extends JpaRepository<ShopEntity, String> {
}
