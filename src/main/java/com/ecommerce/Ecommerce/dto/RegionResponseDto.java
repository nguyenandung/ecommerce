package com.ecommerce.Ecommerce.dto;

import com.ecommerce.Ecommerce.enums.RegionStatus;

import java.util.UUID;
public class RegionResponseDto {
    public String id;
    public String name;
    public String currency;
    public RegionStatus status;

    public RegionResponseDto(String id, String name, String currency, RegionStatus status) {
        this.id = id;
        this.name = name;
        this.currency = currency;
        this.status = status;
    }
}

