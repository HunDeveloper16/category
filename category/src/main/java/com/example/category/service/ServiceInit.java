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
import java.util.ArrayList;
import java.util.List;

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

        List<Category> categories = new ArrayList<>();

        Category category1 = Category.builder()
                .name("도서")
                .upCategory(null)
                .exposureYn("Y")
                .depth(1L)
                .build();
        categories.add(category1);

        Category category2 = Category.builder()
                .name("전공서적")
                .upCategory(null)
                .exposureYn("Y")
                .depth(2L)
                .build();
        category2.changeUpCategory(category1);
        categories.add(category2);

        Category category3 = Category.builder()
                .name("물리학개론")
                .upCategory(null)
                .exposureYn("Y")
                .depth(3L)
                .build();
        category3.changeUpCategory(category2);
        categories.add(category3);

        Category category4 = Category.builder()
                .name("전자")
                .upCategory(null)
                .exposureYn("Y")
                .depth(1L)
                .build();
        categories.add(category4);

        Category category5 = Category.builder()
                .name("직업")
                .upCategory(null)
                .exposureYn("Y")
                .depth(1L)
                .build();
        categories.add(category5);

        Category category6 = Category.builder()
                .name("교육")
                .upCategory(null)
                .exposureYn("N")
                .depth(2L)
                .build();
        category6.changeUpCategory(category5);
        categories.add(category6);

        Category category7 = Category.builder()
                .name("메디컬")
                .upCategory(null)
                .exposureYn("Y")
                .depth(2L)
                .build();
        category7.changeUpCategory(category5);
        categories.add(category7);

        Category category8 = Category.builder()
                .name("교사")
                .upCategory(null)
                .exposureYn("N")
                .depth(3L)
                .build();
        category8.changeUpCategory(category6);
        categories.add(category8);

        Category category9 = Category.builder()
                .name("강사")
                .upCategory(null)
                .exposureYn("N")
                .depth(3L)
                .build();
        category9.changeUpCategory(category6);
        categories.add(category9);

        categoryRepository.saveAll(categories);

        log.info("=================== StartValue Setting Complete ====================");

    }
}
