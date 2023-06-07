package com.ectimel.englishwritingtool.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StageWordDto {
    private Long id;
    private WordDto wordDto;
    private String userTranslation;
    private int correct;
    private int incorrect;
}
