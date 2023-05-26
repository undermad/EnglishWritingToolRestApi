package com.ectimel.englishwritingtool.service;

import com.ectimel.englishwritingtool.dto.CategoryDto;
import com.ectimel.englishwritingtool.dto.PageableResponse;

import java.util.List;

public interface CategoryService {
    PageableResponse getCategory(String categoryName, String sortBy, String sortDirection, int pageNo, int pageSize);
    List<String> getAllCategories();
}
