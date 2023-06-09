package com.ectimel.englishwritingtool.dto;

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
    private String inSentence;
    private String category;
}
