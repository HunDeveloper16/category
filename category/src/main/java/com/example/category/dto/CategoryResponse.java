package com.example.category.dto;

import com.example.category.model.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Data
@ToString
public class CategoryResponse {

    private Long id;
    private String name;
    private Long depth;
    private List<CategoryResponse> children;
    private Long parentId;

    public static CategoryResponse from(Category category) {
        return new CategoryResponse(
                category.getId(),
                category.getName(),
                category.getDepth(),
                category.getChildren().stream().map(CategoryResponse::from).collect(Collectors.toList()),
                category.getUpCategory() != null ? category.getUpCategory().getId() : null
        );
    }

}
