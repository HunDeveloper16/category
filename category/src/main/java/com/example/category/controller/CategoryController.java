package com.example.category.controller;


import com.example.category.dto.CategoryRequest;
import com.example.category.dto.CategoryResponse;
import com.example.category.model.Category;
import com.example.category.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller  // SSR 방식입니다.
@RequiredArgsConstructor
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService categoryService;

    /**
     * 카테고리 목록을 조회하고 목록 페이지를 호출합니다.
     */
    @GetMapping("")
    public String getCategories(Model model){
        List<Category> categories = categoryService.getAllCategories();

        List<CategoryResponse> result = categories.stream().map(CategoryResponse::toExposure).collect(Collectors.toList());

        model.addAttribute("categories",result);
        return "/index";
    }

    /**
     * 미노출된 카테고리 목록을 조회하고 미노출 목록 페이지를 호출합니다.
     */
    @GetMapping("/hidden")
    public String getHiddenCategories(Model model){
        List<Category> categories = categoryService.getAllHiddenCategories();

        List<CategoryResponse> result = categories.stream().map(CategoryResponse::from).collect(Collectors.toList());

        model.addAttribute("categories",result);
        return "/hidden";
    }

    /**
     * 카테고리를 등록하기 위해 페이지를 호출합니다.
     */
    @GetMapping("/regist")
    public String showRegistForm(Model model){
        model.addAttribute("categoryRequest",new CategoryRequest());
        return "/regist";
    }

    /**
     * 카테고리를 등록하고 목록 페이지로 redirect합니다.
     */
    @PostMapping("")
    public String registCategory(@ModelAttribute("categoryRequest") CategoryRequest request){
        categoryService.registCategory(request);

        return "redirect:/category";
    }


    /**
     * 카테고리를 상세 조회하고 조회 페이지를 호출합니다.
     */
    @GetMapping("/detail/{id}")
    public String readCategory(@PathVariable("id") Long id,Model model){
        Category category = categoryService.getCategory(id);

        model.addAttribute("category",CategoryResponse.toDetail(category));

        return "/detail";
    }


}
