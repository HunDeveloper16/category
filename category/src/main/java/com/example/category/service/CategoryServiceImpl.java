package com.example.category.service;

import com.example.category.codeconst.COMMON_YN;
import com.example.category.dto.CategoryRequest;
import com.example.category.model.Category;
import com.example.category.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryServiceImpl implements CategoryService{

    private final CategoryRepository categoryRepository;


    /**
     * 카테고리 목록 조회
     *
     */
    @Transactional(readOnly = true)
    @Override
    public List<Category> getAllCategories() {
        log.info("카테고리의 목록을 조회합니다.");
        return categoryRepository.findAllByExposureAndUpCategory(COMMON_YN.Y.name());
    }


    /**
     * 미노출 카테고리 목록 조회
     *
     */
    @Transactional(readOnly = true)
    @Override
    public List<Category> getAllHiddenCategories() {
        log.info("미노출된 카테고리의 목록을 조회합니다.");
        List<Category> findResult = categoryRepository.findAllByExposure(COMMON_YN.N.name());

        log.info("카테고리를 재귀호출하여 중복 결과를 제거합니다.");
        return removeDuplicates(findResult);

    }

    /**
     * 카테고리 등록
     *
     * @param request 카테고리 등록 객체
     */
    @Transactional
    @Override
    public void registCategory(CategoryRequest request) {
        log.info("카테고리 초기값을 설정합니다. 요청 카테고리 :{}",request);
        Category category = generateRequestDto(request);

        log.info("카테고리를 등록합니다.");
        categoryRepository.save(category);
    }


    /**
     * 카테고리 상세 조회
     *
     * @param id 카테고리 아이디
     */
    @Transactional(readOnly = true)
    @Override
    public Category getCategory(Long id) {
        return categoryRepository.findById(id).orElseThrow(NullPointerException::new); // 에러처리 임시 지정
    }


    /**
     * 카테고리 수정
     *
     * @param id 카테고리 아이디
     */
    @Transactional
    @Override
    public void updateCategory(Long id,String exposureYn) {
        log.info("카테고리의 노출 상태를 수정합니다.");
        Category category = getCategory(id);
        category.setExposureYn(exposureYn);

        checkParentCategory(category);

        checkChildrenCategory(category);
    }


    /**
     * 하위 카테고리를 모두 체크하여 미노출일 경우 하위 카테고리들을 미노출로 변경합니다.
     *
     * @param category 변경할 카테고리 객체
     */
    private void checkChildrenCategory(Category category){
        Set<Category> children = new HashSet<>();
        if(category.isExistChildren()){
            category.getChildren().forEach(cg -> getChildrenCategories(cg.getId(),children));
        }

        if(!category.isExposureY()){
            children.forEach(cg -> {
                if(cg.getExposureYn().equals(COMMON_YN.Y.name())){cg.setExposureYn(COMMON_YN.N.name());}
            });
        }

    }


    /**
     * 부모 카테고리를 모두 체크하여 미노출일 경우 미노출로 변경합니다.
     *
     * @param category 변경할 카테고리 객체
     */
    private void checkParentCategory(Category category) {

        if (category.isExistUpCategory()) {
            Set<Category> categorySet = new HashSet<>();
            getParentCategoryIds(category.getUpCategory().getId(), categorySet);

            boolean containNonExposure = categorySet.stream().anyMatch(cg -> cg.getExposureYn().equals(COMMON_YN.N.name()));
            if (containNonExposure) {
                category.setExposureYn(COMMON_YN.N.name());
            }
        }
    }


    /**
     * 재귀 호출로 모든 부모 객체를 Set에 담습니다.
     *
     * @param id 아이디
     * @param parents 부모 객체
     */
    private void getParentCategoryIds(Long id,Set<Category> parents){
        Category category = getCategory(id);

        if(category.isExistUpCategory()){
            parents.add(category.getUpCategory());
            getParentCategoryIds(category.getUpCategory().getId(),parents);
        }
    }

    /**
     * 재귀 호출로 모든 하위 객체를 Set에 담습니다.
     *
     */
    private void getChildrenCategories(Long id,Set<Category> children){
        Category category = getCategory(id);
        children.add(category);

        if(category.isExistChildren()){
            category.getChildren().forEach(cg-> getChildrenCategories(cg.getId(), children));
        }
    }


    /**
     * 미노출 카테고리의 중복 데이터를 삭제하여 새로운 리스트를 반환합니다.
     * <p> 재귀 호출로 같은 객체 탐색시 탐색 depth가 높을경우 덮혀 씌워지게 하고, depth가 2미만인 데이터를 결과 데이터에 넣어줍니다. </p>
     *
     * @param request 요청 카테고리 리스트 객체
     */
    private List<Category> removeDuplicates(List<Category> request){
        Map<Long,Long> categoryMapByIdAndDepth = new HashMap<>();
        List<Category> result = new ArrayList<>();

        request.forEach(category -> categoryMapByIdAndDepth.put(category.getId(), 1L));

        request.forEach(category -> categoryDepthFirstSearch(category,1L,categoryMapByIdAndDepth));

        request.forEach(category -> {
            if (categoryMapByIdAndDepth.get(category.getId()) < 2) {
                result.add(category);
            }
        });

        return result;
    }


    /**
     * 미노출 카테고리를 추출하는 재귀 함수입니다.
     *
     * @param category 카테고리 객체
     * @param depth 깊이
     * @param map 아이디와 depth를 담을 map
     */
    private void categoryDepthFirstSearch(Category category, Long depth, Map<Long,Long> map){
        Category entity = getCategory(category.getId()); // 반복되는 조회를 개선하기 위하여 BatchSize가 적용됩니다.

        if(map.get(entity.getId())<depth){
            map.put(entity.getId(),depth);
        }
        if(entity.getChildren()!=null && !entity.getChildren().isEmpty()){
            entity.getChildren().forEach(child ->{
                if(child.getExposureYn().equals(COMMON_YN.N.name())) {
                    categoryDepthFirstSearch(child, depth + 1L, map);
                }});
        }
    }


    /**
     * 카테고리 등록시 요청 DTO를 모델로 변환하기 위해 값을 설정합니다.
     *
     * @param request 카테고리 등록 객체
     */
    private Category generateRequestDto(CategoryRequest request){
        if (request.isExistUpCategory()) {
            Category upCategory = getCategory(request.getUpCategoryId());

            request.setUpCategory(upCategory);
            request.setDepth(upCategory.getDepth() + 1L);

            if (request.isYExposureYn() && upCategory.getExposureYn().equals(COMMON_YN.N.name())){
                log.info("부모 카테고리의 노출상태에 따라 미노출로 변경되었습니다.");
                request.setExposureYn(COMMON_YN.N.name());
            }
        }
        return request.toCategory();
    }


}
