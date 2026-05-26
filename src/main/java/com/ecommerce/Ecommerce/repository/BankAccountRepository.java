package com.ecommerce.Ecommerce.repository;

import com.ecommerce.Ecommerce.entity.BankAccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccountEntity, Long> {
    List<BankAccountEntity> findByOwnerIdIn(List<String> ownerIds);
}
