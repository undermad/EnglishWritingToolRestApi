package com.ectimel.englishwritingtool.controller;

import com.ectimel.englishwritingtool.dto.StageWordDto;
import com.ectimel.englishwritingtool.service.StageWordService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/stage-words")
public class StageWordController {

    private final StageWordService stageWordService;

    public StageWordController(StageWordService stageWordService) {
        this.stageWordService = stageWordService;
    }


    @GetMapping("/first-stage")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<StageWordDto>> getFirstStageWords(@RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(stageWordService.getFirstStageWords(token));
    }

    @GetMapping("/second-stage")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<StageWordDto>> getSecondStageWords(@RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(stageWordService.getSecondStageWords(token));
    }

    @GetMapping("/completed-stage")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<StageWordDto>> getCompletedStageWords(@RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(stageWordService.getCompletedStageWords(token));
    }

}
