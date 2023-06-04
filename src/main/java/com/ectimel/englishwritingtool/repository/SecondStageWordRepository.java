package com.ectimel.englishwritingtool.repository;

import com.ectimel.englishwritingtool.entity.FirstStageWord;
import com.ectimel.englishwritingtool.entity.SecondStageWord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SecondStageWordRepository extends JpaRepository<SecondStageWord, Long> {

    @Query("select f from FirstStageWord f where f.user.id=:id")
    List<SecondStageWord> getAllByUserId(Long id);
}
