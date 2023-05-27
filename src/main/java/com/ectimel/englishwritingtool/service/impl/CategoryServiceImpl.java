package com.ectimel.englishwritingtool.service.impl;

import com.ectimel.englishwritingtool.dto.PageableResponse;
import com.ectimel.englishwritingtool.dto.WordDto;
import com.ectimel.englishwritingtool.entity.Category;
import com.ectimel.englishwritingtool.entity.Word;
import com.ectimel.englishwritingtool.repository.CategoryRepository;
import com.ectimel.englishwritingtool.repository.WordRepository;
import com.ectimel.englishwritingtool.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final WordRepository wordRepository;
    private final ModelMapper modelMapper;

    public CategoryServiceImpl(CategoryRepository categoryRepository, WordRepository wordRepository, ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.wordRepository = wordRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public PageableResponse getCategory(String categoryName,
                                        String sortBy,
                                        String sortDirection,
                                        int pageNo,
                                        int pageSize) {

        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name())
                ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        List<Object[]> categoryAsListOfObjects = categoryRepository.getCategoryNameAndId(categoryName);
        Category category = new Category(
                (Long) categoryAsListOfObjects.get(0)[0],
                (String) categoryAsListOfObjects.get(0)[1],
                null);


        Page<Word> wordsAsPage = wordRepository.findAllByCategory(category, pageable);
        List<Word> wordsAsList = wordsAsPage.getContent();
        List<WordDto> words = wordsAsList
                .stream()
                .map((word -> modelMapper.map(word, WordDto.class)))
                .toList();

        return PageableResponse.<WordDto>builder()
                .resourceName(category.getCategoryName())
                .content(words)
                .pageNo(wordsAsPage.getNumber())
                .pageSize(wordsAsPage.getSize())
                .totalPages(wordsAsPage.getTotalPages())
                .totalElements(wordsAsPage.getTotalElements())
                .sortBy(sortBy)
                .build();
    }

    @Override
    public List<String> getAllCategories() {

        List<Object[]> allCategoriesNames = categoryRepository.getAllCategoriesNames();

        return allCategoriesNames
                .stream()
                .map(category -> (String)category[0])
                .collect(Collectors.toList());
    }


}
