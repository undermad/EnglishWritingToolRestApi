package com.ectimel.englishwritingtool.controller;

import com.ectimel.englishwritingtool.dto.CategoryDto;
import com.ectimel.englishwritingtool.dto.PageableResponse;
import com.ectimel.englishwritingtool.dto.WordDto;
import com.ectimel.englishwritingtool.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/{categoryName}")
    public ResponseEntity<PageableResponse<WordDto>> getCategoryByName(
            @PathVariable(name = "categoryName") String categoryName,
            @RequestParam(value = "sortBy", defaultValue = "englishTranslation", required = false) String sortBy,
            @RequestParam(value = "sortDirection", defaultValue = "asc", required = false) String sortDirection,
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "5", required = false) int pageSize) {
        return ResponseEntity.ok(categoryService.getCategory(categoryName, sortBy, sortDirection, pageNo, pageSize));
    }

    @GetMapping
    public ResponseEntity<List<String>> getAllCategories(){
        return ResponseEntity.ok(categoryService.getAllCategories());
    }


}
