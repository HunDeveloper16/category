package com.example.category.repository;

import com.example.category.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category,Long> {


    List<Category> findByUpCategoryIsNull();

}
