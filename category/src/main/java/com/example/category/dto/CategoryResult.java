package com.example.category.dto;

import com.example.category.model.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryResult {
    private Long id;
    private String name;
    private Long depth;
    private List<CategoryResult> children;
    private Long parentId;

    public static CategoryResult of(Category category) {
        return new CategoryResult(
                category.getId(),
                category.getName(),
                category.getDepth(),
                category.getChildren().stream().map(CategoryResult::of).collect(Collectors.toList()),
                category.getParent()==null ? null : category.getParent().getId() // 부모 키값 리턴
        );
    }
}