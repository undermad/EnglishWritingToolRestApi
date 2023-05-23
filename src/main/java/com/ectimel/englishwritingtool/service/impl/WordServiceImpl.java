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

import java.util.List;
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
        Word word = wordAsOptional.get();

        return modelMapper.map(word, WordDto.class);
    }

    @Override
    public WordDto createWord(WordDto wordDto) {

        WordType wordType = getWordTypeName(wordDto.getWordType());

        Optional<Word> wordAsOptional = wordRepository.findByEnglishTranslation(wordDto.getEnglishTranslation());
        if (wordAsOptional.isPresent()) {
            throw new ResourceAlreadyExist("Word", "english translation",
                    wordDto.getEnglishTranslation());
        }


        Word word = modelMapper.map(wordDto, Word.class);
        word.setWordType(wordType);

        return modelMapper.map(wordRepository.save(word), WordDto.class);
    }

    @Override
    public WordDto updateWord(WordDto wordDto) {

        WordType wordType = getWordTypeName(wordDto.getWordType());

        Optional<Word> wordAsOptional = wordRepository.findByEnglishTranslation(wordDto.getEnglishTranslation());
        if (wordAsOptional.isEmpty()) {
            throw new ResourceNotFoundException("Word", "english translation", wordDto.getEnglishTranslation());
        }

        Word word = wordAsOptional.get();
        word.setPolishTranslation(wordDto.getPolishTranslation());
        word.setInSentence(wordDto.getInSentence());
        word.setWordType(wordType);


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

    private WordType getWordTypeName(String wordTypeName) {
        List<Object[]> wordTypeNameResultAsListOfObjects = wordTypeRepository.getWordTypeNameOnly(wordTypeName);
        if (wordTypeNameResultAsListOfObjects.size() == 0) {
            throw new ResourceNotFoundException("Word type", wordTypeName);
        }
        Object[] wordTypeNameResultAsObjects = wordTypeNameResultAsListOfObjects.get(0);

        WordType wordType = new WordType();
        wordType.setId((Long) wordTypeNameResultAsObjects[0]);
        wordType.setTypeName((String) wordTypeNameResultAsObjects[1]);

        return wordType;
    }
}
