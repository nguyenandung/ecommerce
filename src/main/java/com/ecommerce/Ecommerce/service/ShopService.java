package com.ecommerce.Ecommerce.service;

import com.ecommerce.Ecommerce.dto.BankAccountDto;
import com.ecommerce.Ecommerce.dto.CreateListingDto;
import com.ecommerce.Ecommerce.dto.CreateShopDto;
import com.ecommerce.Ecommerce.entity.BankAccountEntity;
import com.ecommerce.Ecommerce.entity.ListingEntity;
import com.ecommerce.Ecommerce.entity.ProductVariant;
import com.ecommerce.Ecommerce.entity.ShopEntity;
import com.ecommerce.Ecommerce.repository.BankAccountRepository;
import com.ecommerce.Ecommerce.repository.ListingRepository;
import com.ecommerce.Ecommerce.repository.ProductVariantRepository;
import com.ecommerce.Ecommerce.repository.ShopRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.*;

@Service
@Transactional
@RequiredArgsConstructor
public class ShopService {
    private final ShopRepository shopRepository;
    private final BankAccountRepository bankAccountRepository;
    private final ListingRepository listingRepository;
    private final ProductVariantRepository productVariantRepository;

    public List<ShopEntity> list(Map<String, Object> params) {
        String variantId = (String) params.get("variantId");
        if(variantId != null) {
           List<ListingEntity> listings = listingRepository.findByVariantIdIn(List.of(variantId));
           List<String> shopIds = new ArrayList<>();
           for(ListingEntity listing : listings){
               String shopId = listing.getShop().getId();
               if(!shopIds.contains(shopId)){
                   shopIds.add(shopId);
               }

           }
            return shopRepository.findAllById(shopIds);
        }

        return shopRepository.findAll();
    }

    public ShopEntity get(String id) {
         ShopEntity shop = shopRepository.findById(id).orElseThrow(
                 () -> new RuntimeException("Shop not found")
         );

         List<BankAccountEntity> bankAccounts = bankAccountRepository.findByOwnerIdIn(List.of(id));
         shop.setBankAccounts(bankAccounts);
         return shop;
    }

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

    @Transactional
    public void createListing(List<CreateListingDto> dto){
        List<ListingEntity> listings = new ArrayList<>();
        List<String> shopIds = new ArrayList<>();
        List<String> variantIds = new ArrayList<>();

        for(CreateListingDto listing : dto) {
            if (!shopIds.contains(listing.getShopId())) {
                shopIds.add(listing.getShopId());
            }
            if(!variantIds.contains(listing.getVariantId())){
                variantIds.add(listing.getVariantId());
            }
        }
        Map<String , ShopEntity> shopMap = new HashMap<>();
        Map<String , ProductVariant> variantMap = new HashMap<>();

        List<ShopEntity> shops = shopRepository.findAllById(shopIds);
        List<ProductVariant> productVariants = productVariantRepository.findAllById(variantIds);

        for(ShopEntity shop : shops) {
            shopMap.put(shop.getId(), shop);
        }

        for(ProductVariant variant : productVariants) {
            variantMap.put(variant.getId(), variant);
        }

        for(CreateListingDto listing : dto) {
            ListingEntity listingEntity = new ListingEntity();
            ShopEntity shop = shopMap.get(listing.getShopId());
            if(shop == null) {
              throw new ResponseStatusException(
                      HttpStatus.NOT_FOUND, "Shop with id " + listing.getShopId() + " not found"
              );
            }
            listingEntity.setShop(shop);

            ProductVariant variant = variantMap.get(listing.getVariantId());
            if(variant == null) {
                throw new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Variant with id " + listing.getVariantId() + " not found"
                );
            }
            listingEntity.setVariant(variant);
            listingEntity.setProductId(listing.getProductId());
            listingEntity.setPrice(listing.getPrice());
            listingEntity.setCurrency(listing.getCurrency());
            listings.add(listingEntity);

        }
        listingRepository.saveAll(listings);


    }
}
