package com.ectimel.englishwritingtool.repository;

import com.ectimel.englishwritingtool.entity.WordType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface WordTypeRepository extends JpaRepository<WordType, Long> {
    Optional<WordType> findByTypeName(String typeName);

    @Query("SELECT w.id, w.typeName from WordType w where w.typeName LIKE CONCAT('%',:query,'%') ")
    List<Object[]> getWordTypeNameOnly(@Param(value = "query") String query);
}
