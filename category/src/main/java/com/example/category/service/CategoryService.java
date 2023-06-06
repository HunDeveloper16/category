package com.example.category.service;

import com.example.category.dto.CategoryRequest;
import com.example.category.dto.CategoryResult;
import com.example.category.model.Category;
import com.example.category.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    /**
     * 카테고리 목록 조회
     */
    public List<CategoryResult> getCategoryList(){

        List<CategoryResult> results = categoryRepository.findAllByParentIsNull().stream().map(CategoryResult::of).collect(Collectors.toList());
        return results;
    }

    /**
     * 카테고리 등록
     */
    public void registCategory(){

        // 1. 부모 카테고리가 없는 경우
        CategoryRequest request = CategoryRequest.builder()
                .name("컴퓨터 전공 서적")
                .upDsplyCtgry(2L)
                .depth(1L)  // 뎁스는 초반 1로 고정
                .build();

        // 상위 카테고리가 존재할시
        if(request.getUpDsplyCtgry()!=null && request.getUpDsplyCtgry() > 0){

            // 상위 카테고리 조회 후 부모객체로 넣어줌
            Optional<Category> categoryOpt = categoryRepository.findById(request.getUpDsplyCtgry());

            // 뎁스는 +1상태로 넣어주며 부모 객체는 부모를 넣어줌
            categoryOpt.ifPresent(category -> categoryRepository.save(
                    Category.builder()
                            .name(request.getName())
                            .depth(category.getDepth() + 1)
                            .parent(category)
                            .build()));

        }
    }

    /**
     * 카테고리 단위 조회
     */
    public CategoryResult getCategoryListByPoint(){

        // 해당 ID와 부모 번호를 같이 리턴해줘야함
        Optional<Category> categoryOpt = categoryRepository.findById(2L);

        return categoryOpt.map(CategoryResult::of).orElse(null);

    }

}
