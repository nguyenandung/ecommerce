package com.ecommerce.Ecommerce.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
//import org.hibernate.query.Page;
import org.springframework.data.domain.Page;
import java.util.List;

@Data
public class PageResponse<T> {
    @JsonProperty("data")
    private List<T> content;
    private int pageNumber;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean isLast;

    public static <T> PageResponse<T> of(Page<T> page) {
        PageResponse<T> response = new PageResponse<>();
        response.setContent(page.getContent());
        response.setPageNumber(page.getNumber());
        response.setPageSize(page.getSize());
        response.setTotalElements(page.getTotalElements());
        response.setTotalPages(page.getTotalPages());
        response.setLast(page.isLast());
        return response;
    }

}
