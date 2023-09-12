# 제목
무한 Depth를 가지는 카테고리 구조를 JPA로 생성해보았습니다.

## 요구사항
1. 카테고리는 자기참조 형태로 설계되어 있습니다.
2. 카테고리 객체는 미노출과 노출 정보를 가집니다.

## 사용한 기술
백엔드는 Springboot,JPA로 개발하였으며 프론트는 최소한의 노출만 표시하도록 Thymeleaf를 사용하여 SSR방식으로 제작하였습니다.

## 카테고리 DB 구조
![image](https://github.com/HunDeveloper16/category/assets/56526225/0357649d-eff7-4709-9fc2-18f437aa9059)
id와 depth, upCategory는 모두 Bigint type을 가지며 나머지는 Varchar(255) type을 가집니다.

## 개발 화면
![image](https://github.com/HunDeveloper16/category/assets/56526225/8fb98f2e-ef06-4053-98aa-d721d55127bb)


## 목록에 관한 처리
![Uploading image.png…]()
