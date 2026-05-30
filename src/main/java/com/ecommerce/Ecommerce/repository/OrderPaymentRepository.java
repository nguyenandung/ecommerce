package com.ecommerce.Ecommerce.repository;

import com.ecommerce.Ecommerce.entity.OrderPaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderPaymentRepository extends JpaRepository<OrderPaymentEntity, String> {
}
