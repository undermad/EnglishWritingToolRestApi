package com.ectimel.englishwritingtool.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class PageableResponse<T> {

    private String resourceName;
    private List<T> content;
    private int pageNo;
    private int pageSize;
    private int totalPages;
    private long totalElements;
    private String sortBy;

}
