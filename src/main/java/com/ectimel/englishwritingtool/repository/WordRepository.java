package com.ectimel.englishwritingtool.repository;

import com.ectimel.englishwritingtool.entity.Category;
import com.ectimel.englishwritingtool.entity.Word;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WordRepository extends JpaRepository<Word, Long> {
    Optional<Word> findByEnglishTranslation(String englishTranslation);
    Page<Word> findAllByCategory(Category category, Pageable pageable);
}
