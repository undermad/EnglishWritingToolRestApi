package com.ectimel.englishwritingtool.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class PageableResponse {

    private String categoryName;
    private List<WordDto> content;
    private int pageNo;
    private int pageSize;
    private int totalPages;
    private long totalElements;
    private String sortBy;
    private String sortDirection;

}
