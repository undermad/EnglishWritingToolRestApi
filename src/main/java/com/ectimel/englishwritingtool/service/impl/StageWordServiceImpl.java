package com.ectimel.englishwritingtool.service.impl;

import com.ectimel.englishwritingtool.dto.StageWordDto;
import com.ectimel.englishwritingtool.dto.WordDto;
import com.ectimel.englishwritingtool.entity.CompletedStageWord;
import com.ectimel.englishwritingtool.entity.FirstStageWord;
import com.ectimel.englishwritingtool.entity.SecondStageWord;
import com.ectimel.englishwritingtool.repository.CompletedStageWordRepository;
import com.ectimel.englishwritingtool.repository.FirstStageWordRepository;
import com.ectimel.englishwritingtool.repository.SecondStageWordRepository;
import com.ectimel.englishwritingtool.security.jwt.JwtTokenProvider;
import com.ectimel.englishwritingtool.service.StageWordService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StageWordServiceImpl implements StageWordService {

    private final JwtTokenProvider jwtTokenProvider;
    private final FirstStageWordRepository firstStageWordRepository;
    private final SecondStageWordRepository secondStageWordRepository;
    private final CompletedStageWordRepository completedStageWordRepository;
    private final ModelMapper modelMapper;

    public StageWordServiceImpl(JwtTokenProvider jwtTokenProvider,
                                FirstStageWordRepository firstStageWordRepository,
                                SecondStageWordRepository secondStageWordRepository,
                                CompletedStageWordRepository completedStageWordRepository,
                                ModelMapper modelMapper) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.firstStageWordRepository = firstStageWordRepository;
        this.secondStageWordRepository = secondStageWordRepository;
        this.completedStageWordRepository = completedStageWordRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public List<StageWordDto> getFirstStageWords(String token) {

        Long userId = jwtTokenProvider.getUserIdFromToken(token);
        List<FirstStageWord> words = firstStageWordRepository.getAllByUserId(userId);

        return words
                .stream()
                .map(word -> {
                    StageWordDto stageWordDto = modelMapper.map(word, StageWordDto.class);
                    WordDto wordDto = modelMapper.map(word.getWord(), WordDto.class);
                    stageWordDto.setWordDto(wordDto);
                    return stageWordDto;
                })
                .toList();

    }

    @Override
    public List<StageWordDto> getSecondStageWords(String token) {

        Long userId = jwtTokenProvider.getUserIdFromToken(token);
        List<SecondStageWord> words = secondStageWordRepository.getAllByUserId(userId);

        return words
                .stream()
                .map(word -> {
                    StageWordDto stageWordDto = modelMapper.map(word, StageWordDto.class);
                    WordDto wordDto = modelMapper.map(word.getWord(), WordDto.class);
                    stageWordDto.setWordDto(wordDto);
                    return stageWordDto;
                })
                .toList();
    }

    @Override
    public List<StageWordDto> getCompletedStageWords(String token) {

        Long userId = jwtTokenProvider.getUserIdFromToken(token);
        List<CompletedStageWord> words = completedStageWordRepository.getAllByUserId(userId);

        return words
                .stream()
                .map(word -> {
                    StageWordDto stageWordDto = modelMapper.map(word, StageWordDto.class);
                    WordDto wordDto = modelMapper.map(word.getWord(), WordDto.class);
                    stageWordDto.setWordDto(wordDto);
                    return stageWordDto;
                })
                .toList();

    }


}
