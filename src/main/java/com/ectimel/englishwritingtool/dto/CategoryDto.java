package com.ectimel.englishwritingtool.dto;

import com.ectimel.englishwritingtool.entity.Word;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {

    private String categoryName;
    private List<WordDto> words;
}
