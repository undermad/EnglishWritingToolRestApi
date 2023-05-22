package com.ectimel.englishwritingtool.service.impl;

import com.ectimel.englishwritingtool.dto.WordDto;
import com.ectimel.englishwritingtool.entity.Word;
import com.ectimel.englishwritingtool.entity.WordType;
import com.ectimel.englishwritingtool.exception.ResourceAlreadyExist;
import com.ectimel.englishwritingtool.exception.ResourceNotFoundException;
import com.ectimel.englishwritingtool.repository.WordRepository;
import com.ectimel.englishwritingtool.repository.WordTypeRepository;
import com.ectimel.englishwritingtool.service.WordService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WordServiceImpl implements WordService {

    private final WordRepository wordRepository;
    private final WordTypeRepository wordTypeRepository;
    private final ModelMapper modelMapper;

    public WordServiceImpl(WordRepository wordRepository, WordTypeRepository wordTypeRepository, ModelMapper modelMapper) {
        this.wordRepository = wordRepository;
        this.wordTypeRepository = wordTypeRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public WordDto getWordByEnglishTranslation(String englishWord) {
        Optional<Word> wordAsOptional = wordRepository.findByEnglishTranslation(englishWord);
        if (wordAsOptional.isEmpty()) {
            throw new ResourceNotFoundException("Word", "english translation", englishWord);
        }

        return modelMapper.map(wordAsOptional.get(), WordDto.class);
    }

    @Override
    public WordDto createWord(WordDto wordDto) {

        Optional<WordType> wordTypeAsOptional = wordTypeRepository.findByTypeName(wordDto.getWordType());
        if (wordTypeAsOptional.isEmpty()){
            throw new ResourceNotFoundException("Word", "type", wordDto.getWordType());
        }
        WordType wordType = wordTypeAsOptional.get();

        Optional<Word> wordAsOptional = wordRepository
                .findByEnglishTranslation(wordDto.getEnglishTranslation());
        if (wordAsOptional.isPresent()) {
            throw new ResourceAlreadyExist("Word",
                    "english translation",
                    wordDto.getEnglishTranslation());
        }


        Word word = modelMapper.map(wordDto, Word.class);
        word.setWordType(wordType);
        return modelMapper.map(wordRepository.save(word), WordDto.class);
    }

    @Override
    public WordDto updateWord(WordDto wordDto) {

        Optional<Word> wordAsOptional = wordRepository.findByEnglishTranslation(wordDto.getEnglishTranslation());
        if (wordAsOptional.isEmpty()) {
            throw new ResourceNotFoundException("Word", "english translation", wordDto.getEnglishTranslation());
        }
        Word word = wordAsOptional.get();
        word.setPolishTranslation(wordDto.getPolishTranslation());
        word.setInSentence(wordDto.getInSentence());


        return modelMapper.map(wordRepository.save(word), WordDto.class);
    }

    @Override
    public String deleteWordByEnglishTranslation(String englishWord) {

        Optional<Word> wordAsOptional = wordRepository.findByEnglishTranslation(englishWord);
        if (wordAsOptional.isEmpty()){
            throw new ResourceNotFoundException("Word", "english translation", englishWord);
        }

        Word wordToDelete = wordAsOptional.get();
        wordRepository.delete(wordToDelete);

        return "Word '" + englishWord + "' has been deleted." ;
    }
}
