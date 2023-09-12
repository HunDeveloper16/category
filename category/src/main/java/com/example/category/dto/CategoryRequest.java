package com.example.category.dto;

import com.example.category.codeconst.COMMON_YN;
import com.example.category.model.Category;
import lombok.Data;

@Data
public class CategoryRequest {

    private String name;

    private Long upCategoryId;

    private String exposureYn;

    private Long depth = 1L;

    private Category upCategory;


    public boolean isExistUpCategory(){
        return upCategoryId!=null;
    }
    public boolean isYExposureYn(){
        return exposureYn!=null && exposureYn.equals(COMMON_YN.Y.name());
    }

    public Category toCategory(){
        return Category.builder()
                .name(name)
                .exposureYn(exposureYn)
                .upCategory(upCategory)
                .depth(depth)
                .build();
    }

}
