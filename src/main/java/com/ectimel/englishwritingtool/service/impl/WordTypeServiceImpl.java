package com.ectimel.englishwritingtool.service.impl;

import com.ectimel.englishwritingtool.entity.WordType;
import com.ectimel.englishwritingtool.exception.ResourceNotFoundException;
import com.ectimel.englishwritingtool.repository.WordTypeRepository;
import com.ectimel.englishwritingtool.service.WordTypeService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WordTypeServiceImpl implements WordTypeService {

    private final WordTypeRepository wordTypeRepository;

    public WordTypeServiceImpl(WordTypeRepository wordTypeRepository) {
        this.wordTypeRepository = wordTypeRepository;
    }

    @Override
    public String getWordType(String typeName) {
        Optional<WordType> wordTypeAsOptional = wordTypeRepository.findByTypeName(typeName);
        if(wordTypeAsOptional.isEmpty()){
            throw new ResourceNotFoundException("Word", "type", typeName);
        }

        WordType wordType = wordTypeAsOptional.get();
        return wordType.getTypeName();
    }

}
