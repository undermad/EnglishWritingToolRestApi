package com.ectimel.englishwritingtool.dto;

import com.ectimel.englishwritingtool.entity.WordType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WordDto {
    private String englishTranslation;
    private String polishTranslation;
    private String inSentence;
    private String wordType;
}
