package com.ecommerce.Ecommerce.service;


import com.ecommerce.Ecommerce.dto.PageResponse;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Sort;


public interface BaseService<T, ID> {
    JpaRepository<T,ID> getRepository();
    default PageResponse<T> listWithPagination(int page , int size,String sortBy, String sortDir){
      Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page,size,sort);
        return PageResponse.of(getRepository().findAll(pageable));
    }
}