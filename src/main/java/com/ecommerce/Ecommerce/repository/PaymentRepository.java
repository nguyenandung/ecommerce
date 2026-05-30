package com.ecommerce.Ecommerce.repository;

import com.ecommerce.Ecommerce.entity.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<PaymentEntity, String> {
}
