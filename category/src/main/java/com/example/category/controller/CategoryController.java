package com.example.category.controller;


import com.example.category.dto.CategoryResponse;
import com.example.category.model.Category;
import com.example.category.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService categoryService;


    @GetMapping("/index")
    public String getCatagory(Model model){

        List<Category> categories = categoryService.getAllCategories();

        // entityToDto
        List<CategoryResponse> result = categories.stream().map(CategoryResponse::from).collect(Collectors.toList());

        model.addAttribute("categories",result);
        return "/index";
    }


}
