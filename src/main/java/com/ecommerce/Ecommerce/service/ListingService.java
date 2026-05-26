package com.ecommerce.Ecommerce.service;

import com.ecommerce.Ecommerce.entity.ListingEntity;
import com.ecommerce.Ecommerce.repository.ListingRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@Transactional
@RequiredArgsConstructor
public class ListingService {
    private final ListingRepository listingRepository;

    public List<ListingEntity> list(Map<String, Object> params) {
        String variantId = (String) params.get("variantId");
        if(variantId != null){
            return listingRepository.findByVariantIdIn(List.of(variantId));
        }
        return listingRepository.findAll();
    }
}
