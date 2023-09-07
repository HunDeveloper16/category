package com.example.category.service;

import com.example.category.dto.CategoryResponse;
import com.example.category.model.Category;
import com.example.category.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService{

    private final CategoryRepository categoryRepository;


    /**
     * 카테고리 목록 조회
     *
     */
    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findByUpCategoryIsNull();
    }


    /**
     * 카테고리 등록
     */
    public void registCategory(){


    }

}
