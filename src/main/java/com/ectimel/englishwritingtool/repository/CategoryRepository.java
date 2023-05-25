package com.ectimel.englishwritingtool.repository;

import com.ectimel.englishwritingtool.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Optional<Category> findByCategoryName(String typeName);

    @Query("SELECT w.id, w.categoryName from Category w where w.categoryName LIKE CONCAT('%',:query,'%') ")
    List<Object[]> getCategoryNameAndId(@Param(value = "query") String query);
}
