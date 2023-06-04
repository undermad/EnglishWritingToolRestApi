package com.ectimel.englishwritingtool.service.impl;

import com.ectimel.englishwritingtool.dto.WordDto;
import com.ectimel.englishwritingtool.entity.Word;
import com.ectimel.englishwritingtool.entity.Category;
import com.ectimel.englishwritingtool.exception.ResourceAlreadyExistException;
import com.ectimel.englishwritingtool.exception.ResourceNotFoundException;
import com.ectimel.englishwritingtool.repository.WordRepository;
import com.ectimel.englishwritingtool.repository.CategoryRepository;
import com.ectimel.englishwritingtool.service.WordService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class WordServiceImpl implements WordService {

    private final WordRepository wordRepository;
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    public WordServiceImpl(WordRepository wordRepository, CategoryRepository categoryRepository, ModelMapper modelMapper) {
        this.wordRepository = wordRepository;
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public WordDto getWordByEnglishTranslation(String englishWord) {
        Optional<Word> wordAsOptional = wordRepository.findByEnglishTranslation(englishWord);
        if (wordAsOptional.isEmpty()) {
            throw new ResourceNotFoundException("Word", "english translation", englishWord);
        }
        Word word = wordAsOptional.get();

        return modelMapper.map(word, WordDto.class);
    }

    @Override
    public WordDto createWord(WordDto wordDto) {

        Category category = getCategoryNameAndId(wordDto.getCategory());

        Optional<Word> wordAsOptional = wordRepository.findByEnglishTranslation(wordDto.getEnglishTranslation());
        if (wordAsOptional.isPresent()) {
            throw new ResourceAlreadyExistException("Word", "english translation",
                    wordDto.getEnglishTranslation());
        }


        Word word = modelMapper.map(wordDto, Word.class);
        word.setCategory(category);

        return modelMapper.map(wordRepository.save(word), WordDto.class);
    }

    @Override
    public WordDto updateWord(WordDto wordDto) {

        Category category = getCategoryNameAndId(wordDto.getCategory());

        Optional<Word> wordAsOptional = wordRepository.findByEnglishTranslation(wordDto.getEnglishTranslation());
        if (wordAsOptional.isEmpty()) {
            throw new ResourceNotFoundException("Word", "english translation", wordDto.getEnglishTranslation());
        }

        Word word = wordAsOptional.get();
        word.setInSentence(wordDto.getInSentence());
        word.setCategory(category);


        return modelMapper.map(wordRepository.save(word), WordDto.class);
    }

    @Override
    public String deleteWordByEnglishTranslation(String englishWord) {

        Optional<Word> wordAsOptional = wordRepository.findByEnglishTranslation(englishWord);
        if (wordAsOptional.isEmpty()) {
            throw new ResourceNotFoundException("Word", "english translation", englishWord);
        }

        Word wordToDelete = wordAsOptional.get();
        wordRepository.delete(wordToDelete);

        return "Word '" + englishWord + "' has been deleted.";
    }

    private Category getCategoryNameAndId(String categoryName) {
        List<Object[]> categoryNameResultAsListOfObjects = categoryRepository.getCategoryNameAndId(categoryName);
        if (categoryNameResultAsListOfObjects.size() == 0) {
            throw new ResourceNotFoundException("Word type", categoryName);
        }
        Object[] wordTypeNameResultAsObjects = categoryNameResultAsListOfObjects.get(0);

        Category category = new Category();
        category.setId((Long) wordTypeNameResultAsObjects[0]);
        category.setCategoryName((String) wordTypeNameResultAsObjects[1]);

        return category;
    }
}
