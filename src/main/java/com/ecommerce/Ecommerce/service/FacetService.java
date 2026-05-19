package com.ecommerce.Ecommerce.service;


import com.ecommerce.Ecommerce.entity.Facet;
import com.ecommerce.Ecommerce.entity.FacetValue;
import com.ecommerce.Ecommerce.repository.FacetRepository;
import com.ecommerce.Ecommerce.repository.FacetValueRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class FacetService {
    private final FacetRepository facetRepository;
    private final FacetValueRepository facetValueRepository;

    public Facet createFacet(String code , String name) {
        Facet facet = new Facet();
        facet.setCode(code);
        facet.setName(name);
       return facetRepository.save(facet);
    }

    public FacetValue createFacetValue(UUID facetId, String code, String name) {
        Facet facet = facetRepository.findById(facetId.toString()).orElseThrow(
                () -> new RuntimeException("facetId not found")
        );
        FacetValue facetValue = new FacetValue();
        facetValue.setCode(code);
        facetValue.setName(name);
        facetValue.setFacet(facet);
        return facetValueRepository.save(facetValue);
    }

    public List<Facet> getAllFacets() {
        return facetRepository.findAllWithFacetValues();
    }
}
