package com.ectimel.englishwritingtool.service;

import com.ectimel.englishwritingtool.dto.CategoryDto;
import com.ectimel.englishwritingtool.dto.PageableResponse;
import com.ectimel.englishwritingtool.dto.WordDto;

import java.util.List;

public interface CategoryService {
    PageableResponse<WordDto> getCategory(String categoryName, String sortBy, String sortDirection, int pageNo, int pageSize);
    List<String> getAllCategories();
}
