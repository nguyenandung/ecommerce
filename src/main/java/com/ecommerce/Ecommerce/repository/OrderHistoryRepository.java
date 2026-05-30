package com.ecommerce.Ecommerce.repository;

import com.ecommerce.Ecommerce.entity.OrderHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderHistoryRepository extends JpaRepository<OrderHistoryEntity, String> {
}
