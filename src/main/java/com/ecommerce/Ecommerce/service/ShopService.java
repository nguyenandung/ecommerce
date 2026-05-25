package com.ecommerce.Ecommerce.service;

import com.ecommerce.Ecommerce.dto.BankAccountDto;
import com.ecommerce.Ecommerce.dto.CreateShopDto;
import com.ecommerce.Ecommerce.entity.BankAccountEntity;
import com.ecommerce.Ecommerce.entity.ShopEntity;
import com.ecommerce.Ecommerce.repository.BankAccountRepository;
import com.ecommerce.Ecommerce.repository.ProductVariantRepository;
import com.ecommerce.Ecommerce.repository.ShopRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@Transactional
@RequiredArgsConstructor
public class ShopService {
    private final ShopRepository shopRepository;
    private final BankAccountRepository bankAccountRepository;
    private final ListingService listingService;
    private final ProductVariantRepository productVariantRepository;

    @Transactional
    public void create(CreateShopDto dto) {
        ShopEntity shopEntity = new ShopEntity();

        shopEntity.setName(dto.name);
        shopEntity.setPhoneNumber(dto.phoneNumber);
        shopEntity.setEmail(dto.email);

        String shopId = shopRepository.save(shopEntity).getId();

        bankAccountRepository.saveAll(
          dto.bankAccounts.stream().map(
                  bankAccount -> new BankAccountEntity(
                          shopId,
                          bankAccount.bankAccount,
                          bankAccount.bankCode,
                          bankAccount.bankName,
                          bankAccount.bankLoginId,
                          bankAccount.bankPassword,
                          BigDecimal.ZERO

                  )
          ).toList()
        );
    }
}
