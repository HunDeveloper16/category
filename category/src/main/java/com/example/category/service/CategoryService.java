package com.example.category.service;

import com.example.category.dto.CategoryRequest;
import com.example.category.model.Category;

import java.util.List;

public interface CategoryService {

    /**
     * 카테고리를 전체 조회합니다.
     */
    List<Category> getAllCategories();

    /**
     * 미노출 카테고리를 전체 조회합니다.
     */
    List<Category> getAllHiddenCategories();

    /**
     * 카테고리를 등록합니다.
     */
    void registCategory(CategoryRequest request);

    /**
     * 카테고리를 상세 조회합니다.
     */
    Category getCategory(Long id);

}
