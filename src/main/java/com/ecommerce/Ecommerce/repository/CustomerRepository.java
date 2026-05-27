package com.ecommerce.Ecommerce.repository;

import com.ecommerce.Ecommerce.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {

}
