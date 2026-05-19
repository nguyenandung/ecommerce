package com.ecommerce.Ecommerce.service;

import com.ecommerce.Ecommerce.entity.ProductOption;
import com.ecommerce.Ecommerce.entity.ProductOptionGroup;
import com.ecommerce.Ecommerce.repository.ProductOptionGroupRepository;
import com.ecommerce.Ecommerce.repository.ProductOptionRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductOptionService {
    private final ProductOptionGroupRepository groupRepository;
    private final ProductOptionRepository optionRepository;

    public ProductOptionGroup createOptionGroup(String code, String name) {
        ProductOptionGroup group = new ProductOptionGroup();
        group.setCode(code);
        group.setName(name);
        return groupRepository.save(group);
    }

    public ProductOption createOptionValue(UUID groupId, String code , String name){
        ProductOptionGroup group = groupRepository.findById(groupId.toString()).orElseThrow(
                () -> new RuntimeException("Option Group not found")
        );
        ProductOption option = new ProductOption();
        option.setCode(code);
        option.setName(name);
        option.setGroup(group);
        return optionRepository.save(option);

    }

    public List<ProductOptionGroup> getAllOptionGroups() {
        return groupRepository.findAllWithOptions();
    }

}
