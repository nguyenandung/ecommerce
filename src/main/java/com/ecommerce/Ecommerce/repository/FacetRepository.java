package com.ecommerce.Ecommerce.repository;

import com.ecommerce.Ecommerce.entity.Facet;
import com.ecommerce.Ecommerce.entity.ProductOptionGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface FacetRepository extends JpaRepository<Facet,String> {
    @Query("SELECT pog FROM ProductOptionGroup pog LEFT JOIN FETCH pog.options")
    List<ProductOptionGroup> findAllWithOptions();

    @Query("SELECT ft FROM Facet ft LEFT JOIN FETCH ft.values ")
    List<Facet> findAllWithFacetValues();
}
