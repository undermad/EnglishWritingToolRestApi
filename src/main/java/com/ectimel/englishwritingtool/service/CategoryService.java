package com.ectimel.englishwritingtool.service;

import com.ectimel.englishwritingtool.dto.CategoryDto;

public interface CategoryService {
    CategoryDto getCategory(String categoryName);
}
