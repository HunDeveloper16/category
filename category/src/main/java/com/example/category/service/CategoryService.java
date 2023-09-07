package com.example.category.service;

import com.example.category.model.Category;

import java.util.List;

public interface CategoryService {

    /**
     * 카테고리 전체 조회
     */
    List<Category> getAllCategories();

}
