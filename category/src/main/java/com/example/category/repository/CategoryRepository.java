package com.example.category.repository;

import com.example.category.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category,Long> {

    // Parent를 null처리 하는 이유는 전체 조회에서 최상위 카테고리 하나를 가져오면 자동으로 children 컬럼이 반환해주기 때문이다.
    List<Category> findAllByParentIsNull();

}
