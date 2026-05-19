package com.ecommerce.Ecommerce.repository;

import com.ecommerce.Ecommerce.entity.ProductOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductOptionRepository extends JpaRepository<ProductOption, String> {
}
