package com.example.category.service;

import com.example.category.model.Category;
import com.example.category.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ServiceInit implements InitializingBean {

    private final CategoryRepository categoryRepository;


    @Override
    public void afterPropertiesSet() throws Exception {
        init();
    }

    public void init(){

        categoryRepository.save(Category.builder()
                .id(1L)
                .name("도서")
                .parent(null)
                .depth(1L)
                .build()
        );

        categoryRepository.save(Category.builder()
                .id(2L)
                .name("전공서적")
                .parent(categoryRepository.findById(1L).get())
                .depth(2L)
                .build()
        );

    }
}
