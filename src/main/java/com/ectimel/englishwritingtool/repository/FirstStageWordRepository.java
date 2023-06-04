package com.ectimel.englishwritingtool.repository;

import com.ectimel.englishwritingtool.entity.FirstStageWord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FirstStageWordRepository extends JpaRepository<FirstStageWord, Long> {

    @Query("select f from FirstStageWord f where f.user.id=:id")
    List<FirstStageWord> getAllByUserId(Long id);
}
