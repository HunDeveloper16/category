package com.example.category.dto;

import com.example.category.codeconst.COMMON_YN;
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
    private Long parentId;
    private List<CategoryResponse> children;

    public static CategoryResponse from(Category category) {
        return new CategoryResponse(
                category.getId(),
                category.getName(),
                category.getDepth(),
                category.getUpCategory() != null ? category.getUpCategory().getId() : null,
                category.getChildren().stream().map(CategoryResponse::from).collect(Collectors.toList())
        );
    }

    public static CategoryResponse toExposure(Category category){
        return new CategoryResponse(
                category.getId(),
                category.getName(),
                category.getDepth(),
                category.getUpCategory() != null ? category.getUpCategory().getId() : null,
                category.getChildren().stream()
                        .filter(child -> child.getExposureYn().equals(COMMON_YN.Y.name()))
                        .map(CategoryResponse::toExposure)
                        .collect(Collectors.toList())
        );
    }

}
