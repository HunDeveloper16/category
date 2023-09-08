package com.example.category.service;

import com.example.category.model.Category;
import com.example.category.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ServerErrorException;

import javax.persistence.Transient;

@Component
@RequiredArgsConstructor
@Slf4j
public class ServiceInit implements InitializingBean {

    private final CategoryRepository categoryRepository;

    @Override
    public void afterPropertiesSet() throws Exception {
        init();
    }

    @Transactional
    public void init() {

        log.info("=================== Create StartValue ====================");
        Category category1 = Category.builder()
                .name("도서")
                .upCategory(null)
                .depth(1L)
                .build();
        Category category2 = Category.builder()
                .name("전공서적")
                .upCategory(null)
                .depth(2L)
                .build();
        Category category3 = Category.builder()
                .name("물리학개론")
                .upCategory(null)
                .depth(3L)
                .build();
        Category category4 = Category.builder()
                        .name("전자")
                .upCategory(null)
                .depth(1L)
                .build();

        categoryRepository.save(category1);
        category2.changeUpCategory(category1);
        categoryRepository.save(category2);
        category3.changeUpCategory(category2);
        categoryRepository.save(category3);
        categoryRepository.save(category4);
        log.info("=================== StartValue Setting Complete ====================");

    }
}
