package com.ectimel.englishwritingtool.repository;

import com.ectimel.englishwritingtool.entity.CompletedStageWord;
import com.ectimel.englishwritingtool.entity.FirstStageWord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CompletedStageWordRepository extends JpaRepository<CompletedStageWord, Long> {

    @Query("select f from FirstStageWord f where f.user.id=:id")
    List<CompletedStageWord> getAllByUserId(Long id);
}
