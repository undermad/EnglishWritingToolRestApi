package com.ectimel.englishwritingtool.service;

import com.ectimel.englishwritingtool.dto.WordDto;

public interface WordService {
    WordDto getWordByEnglishTranslation(String englishWord);
    WordDto createWord(WordDto wordDto);
    WordDto updateWord(WordDto wordDto);
    String deleteWordByEnglishTranslation(String englishWord);
}
