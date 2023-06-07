package com.ectimel.englishwritingtool.consume;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class DictionaryWord {
    private String word;
    private List<DictionaryMeaning> meanings;
}
