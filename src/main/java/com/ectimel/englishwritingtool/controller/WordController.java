package com.ectimel.englishwritingtool.controller;

import com.ectimel.englishwritingtool.dto.WordDto;
import com.ectimel.englishwritingtool.service.WordService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/words")
public class WordController {

    private final WordService wordService;

    public WordController(WordService wordService) {
        this.wordService = wordService;
    }



    @GetMapping("/{englishTranslation}")
    public ResponseEntity<WordDto> getWordByEnglishTranslation(@PathVariable String englishTranslation) {
        WordDto wordDto = wordService.getWordByEnglishTranslation(englishTranslation);
        return ResponseEntity.ok(wordDto);
    }

    @PostMapping
    public ResponseEntity<WordDto> createWord(@RequestBody WordDto wordDto) {
        WordDto createdWord = wordService.createWord(wordDto);
        return new ResponseEntity<>(createdWord, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<WordDto> updateWord(@RequestBody WordDto wordDto){
        WordDto updatedWord = wordService.updateWord(wordDto);
        return ResponseEntity.ok(updatedWord);
    }

    @DeleteMapping("/{englishWord}")
    public ResponseEntity<String> deleteWord(@PathVariable String englishWord){
        return ResponseEntity.ok(wordService.deleteWordByEnglishTranslation(englishWord));
    }

}
