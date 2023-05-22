package com.ectimel.englishwritingtool.repository;

import com.ectimel.englishwritingtool.entity.WordType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WordTypeRepository extends JpaRepository<WordType, Long> {
    Optional<WordType> findByTypeName(String typeName);
}
