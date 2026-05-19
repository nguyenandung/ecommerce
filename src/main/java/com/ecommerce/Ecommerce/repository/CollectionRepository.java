package com.ecommerce.Ecommerce.repository;

import com.ecommerce.Ecommerce.entity.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CollectionRepository extends JpaRepository<Collection, String> {
}
