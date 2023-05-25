package com.ectimel.englishwritingtool.service.impl;

import com.ectimel.englishwritingtool.dto.CategoryDto;
import com.ectimel.englishwritingtool.entity.Category;
import com.ectimel.englishwritingtool.exception.ResourceNotFoundException;
import com.ectimel.englishwritingtool.repository.CategoryRepository;
import com.ectimel.englishwritingtool.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    public CategoryServiceImpl(CategoryRepository categoryRepository, ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public CategoryDto getCategory(String categoryName) {
        Optional<Category> categoryAsOptional = categoryRepository.findByCategoryName(categoryName);
        if(categoryAsOptional.isEmpty()){
            throw new ResourceNotFoundException("Category", categoryName);
        }
        Category category = categoryAsOptional.get();


        return modelMapper.map(category, CategoryDto.class);
    }

}
