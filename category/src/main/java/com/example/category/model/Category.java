package com.example.category.model;

import com.example.category.codeconst.COMMON_YN;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "category")
@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "upCategory")
    private Category upCategory;

    @Column(name = "depth")
    private Long depth;

    @Column(name = "expsrYn")
    private String exposureYn;

    @BatchSize(size = 10)
    @OneToMany(mappedBy = "upCategory")
    @Builder.Default
    private List<Category> children = new ArrayList<>();

    public void setExposureYn(String exposureYn){
        this.exposureYn = exposureYn;
    }

    /**
     * 연관관계 편의 메서드입니다.
     *
     * @param upCategory 부모 카테고리 객체
     */
    public void changeUpCategory(Category upCategory){
        this.upCategory = upCategory;
        upCategory.children.add(this);
    }

    public boolean isExistChildren(){
        return children != null && !children.isEmpty();
    }

    public boolean isExistUpCategory(){
        return upCategory!=null;
    }

    public boolean isExposureY(){
        return exposureYn.equals(COMMON_YN.Y.name());
    }
}
