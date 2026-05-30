package com.ecommerce.Ecommerce.repository;

import com.ecommerce.Ecommerce.entity.OrderItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItemEntity, String> {
}
