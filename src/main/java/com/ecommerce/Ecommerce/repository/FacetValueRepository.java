package com.ecommerce.Ecommerce.repository;

import com.ecommerce.Ecommerce.entity.FacetValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FacetValueRepository extends JpaRepository<FacetValue, String> {

}
