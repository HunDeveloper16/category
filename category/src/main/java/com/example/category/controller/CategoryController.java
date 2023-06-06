package com.example.category.controller;


import com.example.category.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    /**
     * 카테고리 전체 조회
     */
    @GetMapping("/categorys")
    public ResponseEntity<?> getCategoryList() {

        return ResponseEntity.ok(categoryService.getCategoryList());
    }

    /**
     * 카테고리 등록
     */
    @GetMapping("/categorys/regist")
    public ResponseEntity<?> registCategory() {

        categoryService.registCategory();

        return ResponseEntity.ok().build();
    }

    /**
     * 카테고리 단위 조회
     */
    @GetMapping("/categorys/point")
    public ResponseEntity<?> getCategoryListByPoint() {

        return ResponseEntity.ok(categoryService.getCategoryListByPoint());
    }

}
