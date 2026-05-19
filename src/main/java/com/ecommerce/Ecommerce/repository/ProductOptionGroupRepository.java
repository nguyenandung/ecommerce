package com.ecommerce.Ecommerce.repository;

import com.ecommerce.Ecommerce.entity.ProductOptionGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductOptionGroupRepository extends JpaRepository<ProductOptionGroup, String> {
    @Query("SELECT pog FROM ProductOptionGroup pog LEFT JOIN FETCH pog.options")
    List<ProductOptionGroup> findAllWithOptions();
}
