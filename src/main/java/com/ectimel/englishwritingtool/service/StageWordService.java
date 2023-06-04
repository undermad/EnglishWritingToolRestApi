package com.ectimel.englishwritingtool.service;

import com.ectimel.englishwritingtool.dto.StageWordDto;

import java.util.List;

public interface StageWordService {
    List<StageWordDto> getFirstStageWords(String token);
    List<StageWordDto> getSecondStageWords(String token);
    List<StageWordDto> getCompletedStageWords(String token);

}
