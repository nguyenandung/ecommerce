package com.ecommerce.Ecommerce.repository;

import com.ecommerce.Ecommerce.entity.ListingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ListingRepository extends JpaRepository<ListingEntity, String> {
    List<ListingEntity> findByVariantIdIn(List<String> variantIds);
}
