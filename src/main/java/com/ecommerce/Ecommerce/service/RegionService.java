package com.ecommerce.Ecommerce.service;

import com.ecommerce.Ecommerce.entity.Region;
import com.ecommerce.Ecommerce.enums.RegionStatus;
import com.ecommerce.Ecommerce.repository.RegionRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class RegionService {
    private final RegionRepository regionRepository;

    public List<Region> getAllRegions() {
        return regionRepository.findAll();
    }

    public Region createRegion(String code , String name , String currency) {
        String normalizedCode = normalizedCode(code);

        Optional<Region> existing = regionRepository.findByCode(normalizedCode);
        if(existing.isPresent()){
            throw new RuntimeException("Region code already exists");
        }

        Region region = new Region();
        region.setCode(normalizedCode);
        region.setName(name);
        region.setCurrency(currency.toUpperCase(Locale.ROOT));
        region.setStatus(RegionStatus.ACTIVE);
        return regionRepository.save(region);
    }

    public Region getRegionById(UUID id){
        return regionRepository.findById(id.toString()).orElseThrow(
                () -> new RuntimeException("Region not found")
        );
    }

    public Region updateRegion(UUID  id, String name ,String currency,RegionStatus status){
        Region region = getRegionById(id);
        if(name != null && !name.isBlank()){
            region.setName(name);
        }
        if(currency != null && !currency.isBlank()){
            region.setCurrency(currency.toUpperCase(Locale.ROOT));
        }
        if(status != null) {
            region.setStatus(status);
        }
        return regionRepository.save(region);

    }

    public Region changeStatus(UUID id, RegionStatus status ) {
        Region region = getRegionById(id);
        region.setStatus(status);
        return regionRepository.save(region);
    }

    public void deleteRegion(UUID id) {
        Region region = getRegionById(id);
        region.setStatus(RegionStatus.INACTIVE);
        regionRepository.save(region);
    }

    private String normalizedCode(String code){
        if(code == null){
            throw new IllegalArgumentException("Region code cannot be null");
        }

        return code.trim().toUpperCase(Locale.ROOT);
    }

}
