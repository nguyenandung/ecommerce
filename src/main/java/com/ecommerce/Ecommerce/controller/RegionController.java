package com.ecommerce.Ecommerce.controller;

import com.ecommerce.Ecommerce.dto.CreateRegionDto;
import com.ecommerce.Ecommerce.dto.RegionResponseDto;
import com.ecommerce.Ecommerce.dto.UpdateRegionDto;
import com.ecommerce.Ecommerce.entity.Region;
import com.ecommerce.Ecommerce.enums.RegionStatus;
import com.ecommerce.Ecommerce.service.RegionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/regions")
@Tag(name = "Region", description = "Region management APIs")
@RequiredArgsConstructor
public class RegionController {
    private final RegionService regionService;

    @GetMapping
    @Operation(summary = "Get all regions")
    public List<Region> getAllRegions() {
        return regionService.getAllRegions();
    }

    @PostMapping
    @Operation(summary = "Create a new Region")
    public Region createRegion(@RequestBody CreateRegionDto dto) {
        return regionService.createRegion(dto.code,dto.name, dto.currency);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a region by ID")
    public RegionResponseDto getRegionById(@PathVariable String id) {
        Region region = regionService.getRegionById(UUID.fromString(id));
        return new RegionResponseDto(
                region.getId(),
                region.getName(),
                region.getCurrency(),
                region.getStatus()
        );
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a region by ID")
    public RegionResponseDto updateRegion(@PathVariable UUID id, @RequestBody UpdateRegionDto dto) {
        Region region = regionService.updateRegion(id, dto.name, dto.currency, dto.status);
        return new RegionResponseDto(region.getId(),region.getName(),region.getCurrency(),region.getStatus());
    }

    @PatchMapping("/{id}/status")
    @Operation(summary = "Change region status")
    public RegionResponseDto changeRegionStatus(@PathVariable UUID id, @RequestParam("status") RegionStatus status) {
        Region region = regionService.changeStatus(id, status);
        return new RegionResponseDto(region.getId(),region.getName(),region.getCurrency(),region.getStatus());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a region by ID")
    public void deleteRegion(@PathVariable UUID id) {
        regionService.deleteRegion(id);
    }


}
