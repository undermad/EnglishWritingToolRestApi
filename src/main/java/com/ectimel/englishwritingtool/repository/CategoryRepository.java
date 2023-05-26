package com.ectimel.englishwritingtool.repository;

import com.ectimel.englishwritingtool.entity.Category;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query("SELECT w.id, w.categoryName from Category w where w.categoryName LIKE CONCAT('%',:query,'%') ")
    List<Object[]> getCategoryNameAndId(@Param(value = "query") String query);

    @Query("SELECT w.categoryName from Category w")
    List<Object[]> getAllCategoriesNames();

}
