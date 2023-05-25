package com.ectimel.englishwritingtool.controller;

import com.ectimel.englishwritingtool.dto.CategoryDto;
import com.ectimel.englishwritingtool.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/{categoryName}")
    public ResponseEntity<CategoryDto> getCategoryByName(@PathVariable String categoryName){
        return ResponseEntity.ok(categoryService.getCategory(categoryName));
    }



}
