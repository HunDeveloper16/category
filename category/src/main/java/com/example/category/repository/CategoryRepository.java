package com.example.category.repository;

import com.example.category.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {

    @Query(value = "SELECT c FROM Category c WHERE c.upCategory IS NULL AND c.exposureYn = :exposureYn")
    List<Category> findAllByExposureAndUpCategory(@Param("exposureYn") String exposureYn);

    @Query(value = "SELECT c FROM Category c WHERE c.exposureYn = :exposureYn")
    List<Category> findAllByExposure(@Param("exposureYn") String exposureYn);

//    List<Category> findByUpCategoryIsNullAndExposureYnEquals();

}
