package com.ecommerce.Ecommerce.service;

import com.ecommerce.Ecommerce.repository.CustomerRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;



}
